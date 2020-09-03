package com.zainalsalamun.project.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TvShow (
    var id: Int,
    var title: String,
    var rating: Double,
    var overview: String,
    var firstAirDate: String,
    var posterPath: String,
    var backdropPath: String
): Parcelable