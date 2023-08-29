package com.abg.testapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCamera(
    @SerialName("room")
    var room: ArrayList<String>? = null,
    @SerialName("cameras")
    var cameras: ArrayList<Camera>? = null
)