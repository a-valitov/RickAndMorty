package com.avalitov.rickandmorty.model

data class Episode (
    val id : Int,
    val name : String,
    val air_date : String,
    val characters : List<String>
)
