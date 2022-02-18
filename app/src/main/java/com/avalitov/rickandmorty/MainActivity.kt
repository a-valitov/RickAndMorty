package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.adapters.CharacterAdapter
import com.avalitov.rickandmorty.model.Character
import com.avalitov.rickandmorty.response.CharactersResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var charactersArrayList = arrayListOf<Character>()
var currentPage = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCharacters()

        val fab : FloatingActionButton = findViewById(R.id.fab_load)
        fab.setOnClickListener { getCharacters() }
    }

    private fun getCharacters() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCharactersAtPage(currentPage)

        retrofitData.enqueue(object : Callback<CharactersResponse?> {

            override fun onResponse(call: Call<CharactersResponse?>, response: Response<CharactersResponse?>) {
                val responseBody = response.body()?.results
                if (responseBody != null) {
                    charactersArrayList = responseBody
                    val recyclerView: RecyclerView = findViewById(R.id.rv_characters)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = CharacterAdapter(charactersArrayList)

                    if (response.body()!!.info.next != null) {
                        currentPage ++
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Characters not found.", Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onFailure(call: Call<CharactersResponse?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}