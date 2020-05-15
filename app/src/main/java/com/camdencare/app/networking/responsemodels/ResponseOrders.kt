package com.camdencare.app.networking.responsemodels

import com.google.gson.annotations.SerializedName


data class ResponseOrders(
    val orders : List<Orders>
 )

data class Orders (
    val id : Int,
    val order_id : Int,
    val test : String,
    val status : String,
    val created_at : String,
    val patient_name : String,
    val patient_mrn : Int
)