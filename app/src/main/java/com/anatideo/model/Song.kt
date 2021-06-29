package com.anatideo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    val name: String
) : Parcelable