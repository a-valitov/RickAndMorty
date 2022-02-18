package com.avalitov.rickandmorty.response

import com.avalitov.rickandmorty.model.Episode
import com.avalitov.rickandmorty.model.Info

data class EpisodesResponse(
    // this name is exactly the same as in JSON
    val info : Info,
    val results : ArrayList<Episode>
)
