package com.avalitov.rickandmorty

import com.avalitov.rickandmorty.response.CharactersResponse
import com.avalitov.rickandmorty.response.EpisodesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //Call is a Retrofit method that sends a request to a webserver and returns a response
    @GET("character")
    fun getCharactersAtPage(
        @Query("page") page:Int
    ) : Call<CharactersResponse>

    @GET("episode")
    fun getEpisodes() : Call<EpisodesResponse>

}