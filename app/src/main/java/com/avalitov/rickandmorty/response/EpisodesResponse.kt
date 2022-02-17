package com.avalitov.rickandmorty.response

import com.avalitov.rickandmorty.model.Episode

data class EpisodesResponse(
    // this name is exactly the same as in JSON
    val results : ArrayList<Episode>
)
