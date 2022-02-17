package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.adapters.EpisodeAdapter
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
                val episodesArrayList = response.body()?.results
                if (episodesArrayList != null) {
                    val recyclerView: RecyclerView = findViewById(R.id.rv_episodes)
                    recyclerView.layoutManager = LinearLayoutManager(this@EpisodesActivity)
                    recyclerView.adapter = EpisodeAdapter(episodesArrayList)
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