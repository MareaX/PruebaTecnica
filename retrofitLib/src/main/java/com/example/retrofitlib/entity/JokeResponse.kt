package com.example.retrofitlib.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JokeResponse(
    @SerializedName("created_at")
    val startDate: String? = "",
    val id: String? = "",
    @SerializedName("update_at")
    val updateDate: String? = "",
    @SerializedName("value")
    val joke: String
): Parcelable