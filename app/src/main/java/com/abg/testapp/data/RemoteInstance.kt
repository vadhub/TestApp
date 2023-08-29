package com.abg.testapp.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

object RemoteInstance {
    fun client() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true //unquoted string literals are allowed
            })
        }
    }
}