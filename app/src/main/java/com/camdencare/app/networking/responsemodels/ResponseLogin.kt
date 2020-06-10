package com.camdencare.app.networking.responsemodels

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    val mrn: String,
    @SerializedName("full_name") val fullName: String,
    val age: String,
    val gender: String
)