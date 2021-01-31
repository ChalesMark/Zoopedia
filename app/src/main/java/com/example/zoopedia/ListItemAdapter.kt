package com.example.zoopedia

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class ListItemAdapter(private val animals: List<ListItem>, private val database_: List<Animal>, private val context_ : Context) :
    RecyclerView.Adapter<ListItemAdapter.viewHolder>() {

    lateinit var context:Context
    lateinit var database: List<Animal>

    class viewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.viewHolder {
        context = context_
        database = database_
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_animals, parent, false) as View
        val lp = view.layoutParams
        view.layoutParams = lp
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        if (animals[position] != null) {
                var icon: Bitmap = BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.ring_tailed_lemur
                )
                when (animals[position].id) {
                    "RingTailedLemur" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ring_tailed_lemur
                    )
                    "AfricanPenguin" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.african_penguin
                    )
                    "SpottedHyena" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.spotted_hyena
                    )
                    "GreyNeckedCrownedCrane" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.gray_necked_crowned_crane
                    )
                    "GrevysZebra" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.zebra
                    )
                    "OliveBaboon" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.olive_baboon
                    )
                    "WestLowlandGorilla" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.west_low_land_gorillia
                    )
                    "NakedMoleRat" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.naked_mole_rat
                    )
                    "Ostrich" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ostrich
                    )
                    "Warthog" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.warthog
                    )
                    "GoldBreastedStarling" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.golden_breasted_starling_bird
                    )
                    "AfricanLion" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.african_lion
                    )
                    "SpeckledMousebird" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.speckled_mouse_bird
                    )
                    "WestAfricanDwarfCrocodile" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.west_african_dwarf_crocodile
                    )
                    "SpottedNeckOtter" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.spotted_neck_otter
                    )
                    "RoyalPython" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.royal_python
                    )
                    "NileSoftShelledTurtle" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.soft_shell_turtle
                    )
                    "Ngege" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ngege
                    )
                    "RedRiverHog" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.red_river_hog
                    )
                    "AldabraTortoise" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.aldabra_tortoise
                    )
                    "AfricanClawedFrog" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.african_clawed_frog
                    )
                    "LakeMalawiCichlids" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.lake_malawi_cichlids
                    )
                    "SlenderTailedMeerkat" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.meerkat
                    )
                    "BlackCrake" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.black_crake
                    )
                    "BlueBelliedRoller" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.black_bellied_roller
                    )
                    "PygmyHippopotamus" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.pigme_hippo
                    )
                    "GreaterKudu" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.greater_kudu
                    )
                    "SacredIbis" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.sacred_ibis
                    )
                    "Cheetah" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.cheetah
                    )
                    "SouthernGroundHornbill" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.southern_ground_horn_bill
                    )
                    "WhiteRhinoceros" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.white_rhinoceros
                    )
                    "RiverHippopotamus" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.river_hippo
                    )
                    "WhiteCheekedTuraco" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.west_cheeked_turaco
                    )
                    "MellersChameleon" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.mellers_chameleon
                    )
                    "MasaiGiraffe" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.giraffee
                    )
                    "StrawColouredFruitBat" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.straw_colored_fruit_bat
                    )
                    "MarabouStork" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.marabou_stork
                    )
                    "WhiteBreastedCormorant" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.white_breasted_cormorant
                    )
                    "WhiteHeadedVulture" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.white_headed_vulture
                    )
                    "Hamerkop" -> icon = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.hamerkop
                    )
                }
                holder.view.findViewById<ImageView>(R.id.img_listItem_image).setImageBitmap(icon)
                holder.view.findViewById<TextView>(R.id.txt_listItem_name).text = animals[position].name

                holder.view.setOnClickListener(View.OnClickListener {
                    var foundAnimal: Animal? = database.find { a -> a.name == animals[position].name }
                    val jsonString = Gson().toJson(foundAnimal)

                    var intent_animalInfo = Intent(context, AnimalDetails::class.java)
                    intent_animalInfo.putExtra("animalData",jsonString)
                    intent_animalInfo.putExtra("animalImage",animals[position].image)
                    startActivity(context,intent_animalInfo,null)
                })
        }
    }

    override fun getItemCount() = animals.size
}