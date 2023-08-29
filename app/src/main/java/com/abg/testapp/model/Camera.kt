package com.abg.testapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Camera: RealmObject() {
    @PrimaryKey
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