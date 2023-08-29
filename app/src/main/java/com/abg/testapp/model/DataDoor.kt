package com.abg.testapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDoor(
    @SerialName("success")
    var success: Boolean,
    @SerialName("data")
    var doors: ArrayList<Door>? = null
)