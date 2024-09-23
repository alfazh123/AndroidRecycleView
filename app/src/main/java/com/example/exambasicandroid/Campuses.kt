package com.example.exambasicandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Campuses(
    var campusLogo: Int,
    var name: String,
    var shortDesc: String,
    var Location: String,
    var description: String,
    var photo: Int,
) : Parcelable
