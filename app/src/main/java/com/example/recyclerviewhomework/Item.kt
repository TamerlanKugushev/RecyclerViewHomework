package com.example.recyclerviewhomework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val number: String
) : Parcelable