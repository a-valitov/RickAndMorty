package com.avalitov.rickandmorty.response

import com.avalitov.rickandmorty.model.Character

data class CharactersResponse(
    // this name is exactly the same as in JSON
    val results : ArrayList<Character>
)
