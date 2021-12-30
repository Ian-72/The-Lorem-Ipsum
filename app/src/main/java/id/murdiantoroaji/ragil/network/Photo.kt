package id.murdiantoroaji.ragil.network

import com.squareup.moshi.Json

/**
 * class ini digunakan untuk mendefinisikan properti dari response json api.
 * properti response json api berisikan :
 * id type Int, author type String, width type Int, height type Int, url type String,
 * dan yang terakhir download_url dengan type String
 */
data class Photo(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @Json(name = "download_url") val imgSrcUrl: String
)