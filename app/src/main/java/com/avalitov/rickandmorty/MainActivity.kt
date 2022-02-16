package com.avalitov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.response.CharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var tvHello: TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHello = findViewById(R.id.tv_hello)

        getCharacters()
    }

    private fun getCharacters() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCharacters()
        retrofitData.enqueue(object : Callback<CharactersResponse?> {
            override fun onResponse(call: Call<CharactersResponse?>, response: Response<CharactersResponse?>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    val userStringBuilder = StringBuilder()
                    for (dataUnit in responseBody.results) {
                        userStringBuilder.append(dataUnit.toString())
                        userStringBuilder.append("\n")
                    }

                    tvHello.text = userStringBuilder
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