package com.abg.testapp.data

import io.realm.Realm
import io.realm.RealmConfiguration

object DatabaseInstance {

    private val version = 1L

    fun realm(): Realm {
        val config = RealmConfiguration.Builder()
            .name("app.db")
            .schemaVersion(version)
            .build()
        return Realm.getInstance(config)
    }
}