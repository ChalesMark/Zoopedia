package com.example.zoopedia

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson


class AnimalDetails : AppCompatActivity() {

    lateinit var animal: Animal
    lateinit var decodedAnimalImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)
        this.getSupportActionBar()?.hide();

        // Get info from the extras
        val animalData: String? = intent.getStringExtra("animalData")
        val animalImage: String? = intent.getStringExtra("animalImage")

        animal = Gson().fromJson(animalData, Animal::class.java)

        // Disable buttons if links are blank
        if (animal.torontoZooLink == "")
            findViewById<ConstraintLayout>(R.id.btn_torontoZoo).visibility = View.GONE
        if (animal.wikipediaLink == "")
            findViewById<ConstraintLayout>(R.id.btn_wikipedia).visibility = View.GONE
        if (animal.wwfLink == "")
            findViewById<ConstraintLayout>(R.id.btn_wwf).visibility = View.GONE
        if (animal.nationGerographicLink == "")
            findViewById<ConstraintLayout>(R.id.btn_nationalGeographic).visibility = View.GONE

        findViewById<TextView>(R.id.txt_animalName).setText(animal.name)

        var conservationStatusText: String = "ERROR"
        when (animal.conservationStatus){
            0 -> conservationStatusText = applicationContext.getString(R.string.status_0)
            1 -> conservationStatusText = applicationContext.getString(R.string.status_1)
            2 -> conservationStatusText = applicationContext.getString(R.string.status_2)
            3 -> conservationStatusText = applicationContext.getString(R.string.status_3)
            4 -> conservationStatusText = applicationContext.getString(R.string.status_4)
            5 -> conservationStatusText = applicationContext.getString(R.string.status_5)
            6 -> conservationStatusText = applicationContext.getString(R.string.status_6)
            7 -> conservationStatusText = applicationContext.getString(R.string.status_7)
        }

        findViewById<TextView>(R.id.txt_btn_conservationStatus).setText(conservationStatusText)
        findViewById<TextView>(R.id.txt_animalInfo).setText(animal.description)
        findViewById<TextView>(R.id.txt_animalInfo).setMovementMethod(ScrollingMovementMethod())

        // Icon loading
        var icon: Bitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.ring_tailed_lemur
        )
        when (animal.id){
            "RingTailedLemur" -> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.ring_tailed_lemur
            )
            "AfricanPenguin" -> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.african_penguin
            )
            "SpottedHyena" ->  icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.spotted_hyena
            )
            "GreyNeckedCrownedCrane" ->  icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.gray_necked_crowned_crane
            )
            "GrevysZebra"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.zebra
            )
            "OliveBaboon"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.olive_baboon
            )
            "WestLowlandGorilla"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.west_low_land_gorillia
            )
            "NakedMoleRat"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.naked_mole_rat
            )
            "Ostrich"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.ostrich
            )
            "Warthog"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.warthog
            )
            "GoldBreastedStarling"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.golden_breasted_starling_bird
            )
            "AfricanLion"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.african_lion
            )
            "SpeckledMousebird"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.speckled_mouse_bird
            )
            "WestAfricanDwarfCrocodile"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.west_african_dwarf_crocodile
            )
            "SpottedNeckOtter"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.spotted_neck_otter
            )
            "RoyalPython"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.royal_python
            )
            "NileSoftShelledTurtle"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.soft_shell_turtle
            )
            "Ngege"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.ngege
            )
            "RedRiverHog"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.red_river_hog
            )
            "AldabraTortoise"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.aldabra_tortoise
            )
            "AfricanClawedFrog"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.african_clawed_frog
            )
            "LakeMalawiCichlids"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.lake_malawi_cichlids
            )
            "SlenderTailedMeerkat"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.meerkat
            )
            "BlackCrake"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.black_crake
            )
            "BlueBelliedRoller"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.black_bellied_roller
            )
            "PygmyHippopotamus"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.pigme_hippo
            )
            "GreaterKudu"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.greater_kudu
            )
            "SacredIbis"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.sacred_ibis
            )
            "Cheetah"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.cheetah
            )
            "SouthernGroundHornbill"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.southern_ground_horn_bill
            )
            "WhiteRhinoceros"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.white_rhinoceros
            )
            "RiverHippopotamus"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.river_hippo
            )
            "WhiteCheekedTuraco"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.west_cheeked_turaco
            )
            "MellersChameleon"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.mellers_chameleon
            )
            "MasaiGiraffe"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.giraffee
            )
            "StrawColouredFruitBat"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.straw_colored_fruit_bat
            )
            "MarabouStork"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.marabou_stork
            )
            "WhiteBreastedCormorant"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.white_breasted_cormorant
            )
            "WhiteHeadedVulture"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.white_headed_vulture
            )
            "Hamerkop"-> icon = BitmapFactory.decodeResource(
                    getApplicationContext().getResources(),
                    R.drawable.hamerkop
            )
        }

        // Check if animal has a saved image, if so use that, if not use icon
        findViewById<ImageView>(R.id.img_icon).setImageBitmap(icon)
        if (animalImage != "") {
            val decodedString: ByteArray = Base64.decode(animalImage, Base64.DEFAULT)
            decodedAnimalImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            findViewById<ImageView>(R.id.img_animalImage).setImageBitmap(decodedAnimalImage)
        }
        else
            findViewById<ImageView>(R.id.img_animalImage).setImageBitmap(icon)
    }

    // Button Press function
    fun ButtonPress(button: View){
        when (button.id){
            R.id.btn_return->{
                finish()
            }
            R.id.btn_torontoZoo->{
                val linkPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                linkPopup?.setMessage(applicationContext.getString(R.string.txt_details_zoo))?.setTitle(applicationContext.getString(R.string.txt_details_learnMore))
                linkPopup?.apply {
                    setPositiveButton(applicationContext.getString(R.string.txt_details_yes),
                            DialogInterface.OnClickListener { dialog, id ->
                                var intent_website = Intent(context, Website::class.java)
                                intent_website.putExtra("url",animal.torontoZooLink)
                                startActivity(intent_website)
                            })
                    setNegativeButton(applicationContext.getString(R.string.txt_details_no),
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
                }
                linkPopup?.show()
            }
            R.id.btn_wikipedia->{
                val linkPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                linkPopup?.setMessage(applicationContext.getString(R.string.txt_details_wiki))?.setTitle(applicationContext.getString(R.string.txt_details_learnMore))
                linkPopup?.apply {
                    setPositiveButton(applicationContext.getString(R.string.txt_details_yes),
                            DialogInterface.OnClickListener { dialog, id ->
                                var intent_website = Intent(context, Website::class.java)
                                intent_website.putExtra("url",animal.wikipediaLink)
                                startActivity(intent_website)
                            })
                    setNegativeButton(applicationContext.getString(R.string.txt_details_wiki),
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
                }
                linkPopup?.show()
            }
            R.id.btn_wwf->{
                val linkPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                linkPopup?.setMessage(applicationContext.getString(R.string.txt_details_wwf))?.setTitle(applicationContext.getString(R.string.txt_details_learnMore))
                linkPopup?.apply {
                    setPositiveButton(applicationContext.getString(R.string.txt_details_yes),
                            DialogInterface.OnClickListener { dialog, id ->
                                var intent_website = Intent(context, Website::class.java)
                                intent_website.putExtra("url",animal.wwfLink)
                                startActivity(intent_website)
                            })
                    setNegativeButton(applicationContext.getString(R.string.txt_details_no),
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
                }
                linkPopup?.show()
            }
            R.id.btn_nationalGeographic->{
                val linkPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                linkPopup?.setMessage(applicationContext.getString(R.string.txt_details_ng))?.setTitle(applicationContext.getString(R.string.txt_details_learnMore))
                linkPopup?.apply {
                    setPositiveButton(applicationContext.getString(R.string.txt_details_yes),
                            DialogInterface.OnClickListener { dialog, id ->
                                var intent_website = Intent(context, Website::class.java)
                                intent_website.putExtra("url",animal.nationGerographicLink)
                                startActivity(intent_website)
                            })
                    setNegativeButton(applicationContext.getString(R.string.txt_details_no),
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
                }
                linkPopup?.show()
            }
            R.id.btn_conservationStatus-> {
                val conservationPopup: AlertDialog.Builder? = AlertDialog.Builder(this)
                var conservationDesc: String = "";
                when (animal.conservationStatus){
                    0 -> conservationDesc = applicationContext.getString(R.string.status_0_desc)
                    1 -> conservationDesc = applicationContext.getString(R.string.status_1_desc)
                    2 -> conservationDesc = applicationContext.getString(R.string.status_2_desc)
                    3 -> conservationDesc = applicationContext.getString(R.string.status_3_desc)
                    4 -> conservationDesc = applicationContext.getString(R.string.status_4_desc)
                    5 -> conservationDesc = applicationContext.getString(R.string.status_5_desc)
                    6 -> conservationDesc = applicationContext.getString(R.string.status_6_desc)
                    7 -> conservationDesc = applicationContext.getString(R.string.status_7_desc)
                }
                conservationPopup?.setMessage(conservationDesc)?.setTitle(findViewById<TextView>(R.id.txt_btn_conservationStatus).text)
                conservationPopup?.apply {
                    setNeutralButton(applicationContext.getString(R.string.conservationLearnMore),
                            DialogInterface.OnClickListener { dialog, id ->
                                var intent_website =  Intent(getContext(),Website::class.java)
                                intent_website.putExtra("url","https://www.iucnredlist.org/")
                                startActivity(intent_website)
                            })
                }
                conservationPopup?.show()
            }
        }
    }
}