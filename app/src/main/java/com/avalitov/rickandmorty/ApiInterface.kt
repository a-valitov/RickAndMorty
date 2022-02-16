package com.avalitov.rickandmorty

import com.avalitov.rickandmorty.response.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //Call is a Retrofit method that sends a request to a webserver and returns a response

    @GET("character")
    fun getCharacters() : Call<CharactersResponse>

}