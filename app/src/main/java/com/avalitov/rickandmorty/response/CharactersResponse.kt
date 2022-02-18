package com.avalitov.rickandmorty.response

import com.avalitov.rickandmorty.model.Character
import com.avalitov.rickandmorty.model.Info

data class CharactersResponse(
    // this name is exactly the same as in JSON
    val info : Info,
    val results : ArrayList<Character>
)
