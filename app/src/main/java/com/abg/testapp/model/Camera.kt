package com.abg.testapp.model

import io.realm.kotlin.types.RealmObject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Camera: RealmObject {
    @SerialName("id")
    var id: Int = 0

    @SerialName("name")
    var name: String = ""

    @SerialName("snapshot")
    var snapshot: String = ""

    @SerialName("room")
    var room: String? = null

    @SerialName("favorites")
    var favorites: Boolean = false

    @SerialName("rec")
    var rec: Boolean = false
}