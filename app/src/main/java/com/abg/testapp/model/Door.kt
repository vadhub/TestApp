package com.abg.testapp.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Door : RealmObject {
    @PrimaryKey
    @SerialName("id")
    var id: Int = 0

    @SerialName("name")
    var name: String = ""

    @SerialName("room")
    var room: String? = null

    @SerialName("favorites")
    var favorites: Boolean = false

    @SerialName("snapshot")
    var snapshot: String = ""
}