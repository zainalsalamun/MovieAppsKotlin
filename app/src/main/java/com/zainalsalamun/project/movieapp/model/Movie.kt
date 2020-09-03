package com.zainalsalamun.project.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie (
    var id : Int,
    var title : String,
    var rating : Double,
    var overview : String,
    var releaseDate : String,
    var posterPath : String,
    var backdropPath : String
):Parcelable

