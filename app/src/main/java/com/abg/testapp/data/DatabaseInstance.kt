package com.abg.testapp.data

import com.abg.testapp.model.Camera
import com.abg.testapp.model.Door
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object DatabaseInstance {
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Door::class, Camera::class
            )
        )
            .name("app.db")
            .schemaVersion(1)
            .build()
        return Realm.open(config)
    }
}