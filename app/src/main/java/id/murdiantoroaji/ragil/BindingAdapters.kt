package id.murdiantoroaji.ragil

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.murdiantoroaji.ragil.network.Photo
import id.murdiantoroaji.ragil.overview.ApiStatus
import id.murdiantoroaji.ragil.overview.PhotoGridAdapter

/*
 * @BindingAdapter memberi tahu data binding
 * untuk mengeksekusi adaptor binding ini
 * jika item Tampilan memiliki atribut imageUr
 * Fungsi Bindimage untuk membinding gambar
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        /*
        * mengonversi string URL menjadi objek Uri
        * menggunakan metode toUri() dengan skema https ke builder toUri.
        * build() untuk membuat objek.
         */
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        // memuat gambar dari objek imgUri ke imgView
        imgView.load(imgUri) {
            // menetapkan jika gambar sedang dimuat
            placeholder(R.drawable.loading_animation)
            // menetapkan jika gambar rusak/tidak ada koneksi
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Fungsi untuk membiding RecyclerView
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    // membuat variabel adapter yang di isi adapter.submitList()
    val adapter = recyclerView.adapter as PhotoGridAdapter
    // memanggil adapter.submitList() dengan data daftar foto
    adapter.submitList(data)
}

/**
 * Fungsi untuk membinding status
 */
@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    // Ketika status bernilai benar
    when (status) {
        // set menjadi ApiStatus.LOADING
        ApiStatus.LOADING -> {
            // setel ImageView menjadi terlihat dengan melampirkan icon loading
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        // set menjadi ApiStatus.ERROR
        ApiStatus.ERROR -> {
            // setel ImageView menjadi terlihat dengan melampirkan icon koneksi error
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        // set menjadi ApiStatus.DONE
        ApiStatus.DONE -> {
            // setel ImageView menjadi hilang alias selesai
            statusImageView.visibility = View.GONE
        }
    }
}