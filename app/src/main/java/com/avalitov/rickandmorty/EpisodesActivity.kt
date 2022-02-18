package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.adapters.EpisodeAdapter
import com.avalitov.rickandmorty.model.Episode
import com.avalitov.rickandmorty.response.EpisodesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var episodesArrayList = ArrayList<Episode>()
private var currentPage : Int = 1
private lateinit var recyclerView : RecyclerView

class EpisodesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        val characterId = intent.extras?.get("character Id") as String

        recyclerView = findViewById(R.id.rv_episodes)
        recyclerView.layoutManager = LinearLayoutManager(this@EpisodesActivity)
        recyclerView.adapter = EpisodeAdapter(episodesArrayList)

        while (currentPage <= 3) {
            getEpisodesWithCharacter(characterId)
            currentPage++
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        currentPage = 1
        episodesArrayList.clear()
    }

    private fun getEpisodesWithCharacter(id: String) {
        // TODO: while (next != null)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getEpisodesAtPage(currentPage)

        retrofitData.enqueue(object : Callback<EpisodesResponse?> {

            override fun onResponse(call: Call<EpisodesResponse?>, response: Response<EpisodesResponse?>) {
                val responseBody = response.body()?.results
//                if (responseBody != null) {
//                    episodesArrayList.addAll(responseBody)
//
//                }



                if (responseBody != null) {

                    // Taking only these Episodes where picked Character appears
                    val newEpisodesArrayList = responseBody
                        .filter { it.characters.contains(FILTER_TEMPLATE + id) }
                            as ArrayList<Episode>

                    episodesArrayList.addAll(newEpisodesArrayList)
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@EpisodesActivity, "Episodes not found.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EpisodesResponse?>, t: Throwable) {
                Log.d("EpisodesActivity", "onFailure: " + t.message)
            }
        })
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val FILTER_TEMPLATE = BASE_URL + "character/"
        const val PAGE_SIZE = 20
    }
}