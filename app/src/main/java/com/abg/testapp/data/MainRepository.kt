package com.abg.testapp.data

import com.abg.testapp.model.Camera
import com.abg.testapp.model.DataDoor
import com.abg.testapp.model.Door
import com.abg.testapp.model.Root
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.realm.Realm
import java.lang.Exception

private const val BASE_URL = "http://cars.cprogroup.ru/api/rubetek"
private const val GET_CAMERA = "$BASE_URL/cameras/"
private const val GET_DOOR = "$BASE_URL/doors/"

class MainRepository(
    private val client: HttpClient,
    private val database: Realm
) {
    suspend fun getDoors(): Resource<List<Door>?> {
        return try {
            Resource.Success(
                client.get<DataDoor>{
                    url(GET_DOOR)
                }.doors
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    suspend fun getCameras():Resource<List<Camera>?> {
        return try {
            Resource.Success(
                client.get<Root>{
                    url(GET_CAMERA)
                }.data?.cameras
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}