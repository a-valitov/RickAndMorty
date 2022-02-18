package com.avalitov.rickandmorty.model

// HTTP request information
data class Info(
    val pages : Int,
    val next : String?,
    val prev : String?
)
