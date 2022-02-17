package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.model.Character
import com.avalitov.rickandmorty.response.CharactersResponse
import com.avalitov.rickandmorty.response.EpisodesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        val characterId = intent.extras?.get("character Id") as Int

        getEpisodesWithCharacter(characterId)
    }

    private fun getEpisodesWithCharacter(id: Int) {
        // TODO: while (next != null)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getEpisodes()

        retrofitData.enqueue(object : Callback<EpisodesResponse?> {

            override fun onResponse(call: Call<EpisodesResponse?>, response: Response<EpisodesResponse?>) {
                val responseBody = response.body()?.results
                if (responseBody != null) {
                    val episodesArrayList = responseBody

                    // TODO: Show in RecyclerView
                    val userStringBuilder = StringBuilder()
                    for (dataUnit in responseBody) {
                        userStringBuilder.append(dataUnit.name)
                        userStringBuilder.append("\n")
                    }
                    // TODO: remove test output
                    Toast.makeText(this@EpisodesActivity, userStringBuilder.toString(), Toast.LENGTH_LONG).show()

//                    val recyclerView: RecyclerView = findViewById(R.id.rv_characters)
//                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                    recyclerView.adapter = CharacterAdapter(charactersArrayList)
                } else {
                    Toast.makeText(this@EpisodesActivity, "Episodes not found.", Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<EpisodesResponse?>, t: Throwable) {
                Log.d("EpisodesActivity", "onFailure: " + t.message)
            }
        })
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}