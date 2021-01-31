package com.example.zoopedia

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerLocalModel
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerOptions
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    val autoMLName = "animals_2020112123352"
    lateinit var labeler:ImageLabeler
    lateinit var animals: List<Animal>
    lateinit var animalsAlreadySaved: MutableList<ListItem>
    lateinit var sharedPref : SharedPreferences

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getSupportActionBar()?.hide();

        // Get the labeler setup
        try {
            val localModel = AutoMLImageLabelerLocalModel.Builder()
                    .setAssetFilePath("MLModel/manifest.json").build()
            val autoMLImageLabelerOptions = AutoMLImageLabelerOptions.Builder(localModel)
                    .setConfidenceThreshold(0.5f).build()
            labeler = ImageLabeling.getClient(autoMLImageLabelerOptions)
        } catch (exception: Exception) {
            Log.d("ERROR!",""+exception)
        }

        // Load animal database
        try {
            val animalDatabase: String? =
                assets.open("animalData.json").bufferedReader().use { it.readText() }
            val animalType = object : TypeToken<List<Animal>>() {}.type
            animals = Gson().fromJson(animalDatabase, animalType)
        } catch (exception: Exception) {
            Log.d("ERROR!",""+exception)
        }

        // Load animals alread collected
        animalsAlreadySaved = mutableListOf<ListItem>()
        try {
            sharedPref = applicationContext.getSharedPreferences(
                getString(R.string.zoopediaKey), MODE_PRIVATE)
            val animalsSaved: String? = sharedPref.getString("collected","")

            val listItemType = object : TypeToken<List<ListItem>>() {}.type
            animalsAlreadySaved = Gson().fromJson(animalsSaved, listItemType)
        } catch (exception: Exception) {
            Log.d("ERROR!",""+exception)
        }
        viewAdapter = ListItemAdapter(animalsAlreadySaved, animals, this)
        var gridLayoutManager :GridLayoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        var animalList:RecyclerView = findViewById<RecyclerView>(R.id.rv_animalList)
        animalList.adapter = viewAdapter
        animalList.layoutManager = gridLayoutManager
    }

    val REQUEST_IMAGE_CAPTURE = 1

    // Camera Button Press
    public fun CameraButton(view: View){
        Log.d("ASDASDASD",animalsAlreadySaved.toString())
        dispatchTakePictureIntent();
    }

    // The actual intent start for the camera
    private fun dispatchTakePictureIntent() {
        try {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: Exception) {
            print(e.toString())
        }
    }

    // This is used to get the bitmap into a proper format
    private fun formatImage (image: Bitmap): Bitmap {
        var imageToBeConverted: Bitmap = image;
        val rotationMatrix = Matrix()
        if (imageToBeConverted.getWidth() >= imageToBeConverted.getHeight()) {
            rotationMatrix.setRotate(90f)
        } else {
            rotationMatrix.setRotate(0f)
        }

        imageToBeConverted = Bitmap.createBitmap(
                imageToBeConverted,
                0,
                0,
                imageToBeConverted.getWidth(),
                imageToBeConverted.getHeight(),
                rotationMatrix,
                true
        )
        return imageToBeConverted
    }

    // This is for when the image gets back from the camera intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            var imageBitmap: Bitmap? = null;
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                imageBitmap = data?.extras?.get("data") as Bitmap
                val rotationMatrix = Matrix()
                if (imageBitmap.getWidth() >= imageBitmap.getHeight()) {
                    rotationMatrix.setRotate(90f)
                } else {
                    rotationMatrix.setRotate(0f)
                }

                imageBitmap = Bitmap.createBitmap(
                        imageBitmap,
                        0,
                        0,
                        imageBitmap.getWidth(),
                        imageBitmap.getHeight(),
                        rotationMatrix,
                        true
                )
            }
            val image = imageBitmap?.let { InputImage.fromBitmap(it, 0) }
            if (image != null) {
                if (labeler != null) {
                    labeler.process(image)
                            .addOnSuccessListener { labels ->
                                if (labels.size > 0) {

                                    // Find animal
                                    var foundAnimal: Animal? =
                                        animals.find { a -> a.id == labels[0].text }

                                    if (foundAnimal != null) {
                                        val jsonString = Gson().toJson(foundAnimal)  // json string
                                        var intent_animalInfo =
                                            Intent(this, AnimalDetails::class.java)

                                        val byteArrayOutputStream = ByteArrayOutputStream()
                                        imageBitmap?.compress(
                                            Bitmap.CompressFormat.JPEG,
                                            10,
                                            byteArrayOutputStream
                                        )
                                        val byteArray = byteArrayOutputStream.toByteArray()

                                        // Add animal data to intent
                                        intent_animalInfo.putExtra(
                                            "animalData",
                                            jsonString
                                        )
                                        intent_animalInfo.putExtra(
                                            "animalImage",
                                            Base64.encodeToString(byteArray, Base64.DEFAULT)
                                        )

                                        // Check if animal has already been collected. If not, add. If yes, update photo.
                                        var newEntry:ListItem = ListItem(foundAnimal.id,foundAnimal.name,Base64.encodeToString(byteArray, Base64.DEFAULT))
                                        if ((animalsAlreadySaved.find{ animal -> animal.name == foundAnimal.name }) == null) {
                                            animalsAlreadySaved.add(newEntry)
                                            viewAdapter.notifyItemInserted(animalsAlreadySaved.size)
                                        }
                                        else
                                            try {
                                                animalsAlreadySaved.find { a -> a.name == newEntry.name }!!.image = newEntry.image
                                                viewAdapter.notifyItemChanged(animalsAlreadySaved.indexOfFirst { a -> a.name == newEntry.name })
                                            }
                                            catch(e:java.lang.Exception){}

                                        // Save intent
                                        with(sharedPref.edit()) {
                                            putString("collected", Gson().toJson(animalsAlreadySaved))
                                            apply()
                                        }

                                        startActivity(intent_animalInfo)
                                    }
                                }
                            }
                            .addOnFailureListener { e ->
                            }
                }
            }
        }
        catch(ex:Exception){
            Log.d("ERROR!",""+ex)
        }
    }

    // Function for deleting all animals colelcted by user
    fun DeleteData(){
        if (animalsAlreadySaved.size > 0)
            try {
                Log.d("asd","1")
                viewAdapter.notifyItemRangeRemoved(0, animalsAlreadySaved.size)
                Log.d("asd","2")
                animalsAlreadySaved.clear()
                var removeSP = applicationContext.getSharedPreferences(
                        getString(R.string.zoopediaKey), MODE_PRIVATE).edit()
                removeSP.clear()
                removeSP.commit()
            }
            catch (e:java.lang.Exception){}
    }

    // Function for adding all the animals to the list
    fun AddAllAnimals(){
        for (a in animals){
            if ((animalsAlreadySaved.find{ animal -> animal.name == a.name }) == null) {
                animalsAlreadySaved.add(ListItem(a.id,a.name,""))
                viewAdapter.notifyItemInserted(animalsAlreadySaved.size)
            }
        }
        with(sharedPref.edit()) {
            putString("collected", Gson().toJson(animalsAlreadySaved))
            apply()
        }
    }

    // Options button
    fun Options(view:View){
        val optionsPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
        optionsPopup?.setTitle(applicationContext.getString(R.string.optionTitle))?.setItems(R.array.sa_options,
                DialogInterface.OnClickListener { dialog, clicked ->
                    when(clicked){
                        0 -> {
                            val aboutPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                            aboutPopup?.setMessage(applicationContext.getString(R.string.about_text))?.setTitle(applicationContext.getString(R.string.about_title))
                            aboutPopup?.show()
                        }
                        1 -> DeleteData()
                        2 -> AddAllAnimals()
                    }
                })
        optionsPopup?.show()
    }
}

