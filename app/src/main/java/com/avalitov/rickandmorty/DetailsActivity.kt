package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.avalitov.rickandmorty.model.Character
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val character = intent.extras?.get("selected character") as Character
        // TODO: remove test output
        Toast.makeText(this, character.toString(), Toast.LENGTH_LONG).show()

        val ivPicture : ImageView = findViewById(R.id.iv_details_picture)
        val tvName : TextView = findViewById(R.id.tv_details_name)
        val tvLocation : TextView = findViewById(R.id.tv_details_location_name)
        val tvSpecies : TextView = findViewById(R.id.tv_details_species)
        val tvStatus : TextView = findViewById(R.id.tv_details_status)

        if (character != null) {
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
    }
}