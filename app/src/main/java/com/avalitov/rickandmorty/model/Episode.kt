package com.avalitov.rickandmorty.model

import android.os.Parcel
import android.os.Parcelable
import retrofit2.http.Url

data class Episode (
    val id : Int?,
    val name : String?,
    val air_date : String?
)
