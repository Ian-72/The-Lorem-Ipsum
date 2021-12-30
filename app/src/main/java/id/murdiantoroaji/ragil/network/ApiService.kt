package id.murdiantoroaji.ragil.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Base URL REST API dari picsum photos
private const val BASE_URL = "https://picsum.photos"

/**
 * Membuat Moshi object degan Kotlin adapater factory
 * yang akan digunakan oleh Retrofit
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Retrofit object dengan Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Interface Public ApiService
 * dengan method getPhotos() (menampilkan data api)
 */
interface ApiService {
    // mendapatkan foto dengan method GET pada endpoint /v2/list
    @GET("v2/list?limit=50") // limit 50
    suspend fun getPhotos() : List<Photo>
}

// Deklarasi public Object Api
object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}
