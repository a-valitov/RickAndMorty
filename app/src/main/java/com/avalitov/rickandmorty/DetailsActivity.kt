package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val position = intent.extras?.get("EXTRA_MESSAGE") as String
        Toast.makeText(this, position, Toast.LENGTH_LONG).show()
    }
}