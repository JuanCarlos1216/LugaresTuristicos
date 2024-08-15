package com.juanca.exploreit.models

import com.google.gson.annotations.SerializedName

data class PlacesDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("data")
    val data: List<Places>
)