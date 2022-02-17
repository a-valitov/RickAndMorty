package com.avalitov.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.avalitov.rickandmorty.model.Character
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Getting the character from the intent
        character = intent.extras?.get("selected character") as Character
        
        val ivPicture : ImageView = findViewById(R.id.iv_details_picture)
        val tvName : TextView = findViewById(R.id.tv_details_name)
        val tvLocation : TextView = findViewById(R.id.tv_details_location_name)
        val tvSpecies : TextView = findViewById(R.id.tv_details_species)
        val tvStatus : TextView = findViewById(R.id.tv_details_status)

        // Filling activity fields with character's data
        Glide.with(ivPicture)
            .load(character.image)
            .placeholder(R.drawable.ic_character_placeholder)
            .error(R.drawable.ic_character_placeholder)
            .circleCrop()
            .into(ivPicture)
        tvName.text = character.name
        tvLocation.text = character.location?.name
        tvSpecies.text = character.species
        tvStatus.text = character.status
    }

    fun onClickShowEpisodes(view: View) {
        val intent = Intent(view.context, EpisodesActivity::class.java).apply {
            putExtra("character Id", character.id)
        }
        ContextCompat.startActivity(view.context, intent, null)
    }
}