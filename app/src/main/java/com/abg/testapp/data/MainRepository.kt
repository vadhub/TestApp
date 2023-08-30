package com.abg.testapp.data

import android.util.Log
import com.abg.testapp.model.Camera
import com.abg.testapp.model.DataDoor
import com.abg.testapp.model.Door
import com.abg.testapp.model.Root
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * url constants
 * */
private const val BASE_URL = "http://cars.cprogroup.ru/api/rubetek"
private const val GET_CAMERA = "$BASE_URL/cameras/"
private const val GET_DOOR = "$BASE_URL/doors/"

/**
 * Class repository for handling data from Realm database and remote server
 * @param [client] is instance client http
 * @param [realm] is instance Realm database
 * */

class MainRepository(
    private val client: HttpClient,
    private val realm: Realm
) {

    /** check isEmpty Door in Database
     * @return true if isEmpty
     * */
    fun checkIsEmptyDoorDB(): Boolean {
        val list = realm.query<Door>().find()

        if (list.isEmpty()) {
            return true
        }
        return false
    }

    /** check isEmpty Camera in Database
     * @return true if isEmpty
     * */
    fun checkIsEmptyCameraDB(): Boolean {
        val list = realm.query<Camera>().find()

        if (list.isEmpty()) {
            return true
        }
        return false
    }

    /** get request on remote server.
     * @return [com.abg.testapp.data.Resource]
     */
    suspend fun getDoorsFromRemote(): Resource<List<Door>?> {
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

    /**
     * get all Doors [com.abg.testapp.model.Door] from Database
     * @return [kotlinx.coroutines.flow.Flow]
     * */
    fun getDoorsFromDB(): Flow<List<Door>> {
        return realm.query<Door>().asFlow().map { it.list }
    }

    /**
     * insert all Doors [com.abg.testapp.model.Door] to Database
     * */
    suspend fun insertAllDoors(doors: List<Door>) {
            if (doors.isNotEmpty()) {
                Log.d("insert", doors.toTypedArray().contentToString())
                doors.forEach { realm.write { this.copyToRealm(it) } }
            }

    }

    /**
     * insert favorite Door
     * @param [id] is favorite door
     * */
    suspend fun insertOrUpdateFavoriteDoor(id: Int) {
        realm.write {
            val d = this.query<Door>("id == $0", id).first().find()
            if (d != null) {
                d.favorites= !d.favorites
            }
        }
    }

    /**
     * insert renamed Door
     * @param [id] is renamed door
     * @param [newName] is new name door
     * */
    suspend fun insertOrUpdateRenamedDoor(id: Int, newName: String) {
        realm.write {
            val d = this.query<Door>("id == $0", id).first().find()
            if (d != null) {
                d.name = newName
            }
        }
    }

    /** get request on remote server.
     * @return [com.abg.testapp.data.Resource]
     */
    suspend fun getCamerasFromRemote():Resource<List<Camera>?> {
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

    /**
     * get all Cameras [com.abg.testapp.model.Camera] from Database
     * @return [kotlinx.coroutines.flow.Flow]
     * */
    fun getCamerasFromDB(): Flow<List<Camera>> {
        return realm.query<Camera>().asFlow().map { it.list }
    }

    /**
     * insert all Cameras [com.abg.testapp.model.Camera] to Database
     * */
    suspend fun insertAllCameras(cameras: List<Camera>) {
        if (cameras.isNotEmpty()) {
            cameras.forEach { realm.write { this.copyToRealm(it) } }
        }
    }

    /**
     * insert favorite Camera
     * @param [id] is renamed camera
     * */
    suspend fun insertOrUpdateFavoriteCamera(id: Int) {
        realm.write {
            val cam = this.query<Camera>("id == $0",id).first().find()
            if (cam != null) {
                cam.favorites= !cam.favorites
            }
        }
    }
}