package id.murdiantoroaji.ragil.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.murdiantoroaji.ragil.databinding.GridViewItemBinding
import id.murdiantoroaji.ragil.network.Photo

/**
 * Class PhotoGridAdapter dengan ListAdapter,
 * yang konstruktornya menggunakan daftar item,
 * holder tampilan, dan implementasi DiffUtil.ItemCallback
 */
class PhotoGridAdapter: ListAdapter<Photo, PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {
    /**
     * PhotoViewHolder, yang memperluas RecyclerView.ViewHolder.
     * GridViewItemBinding untuk mem-binding Photo ke tata letak,
     * ViewHolder dengan tampilan root binding
     */
    class PhotoViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        /**
         * metode bind() yang menggunakan objek Photo sebagai argumen
          */
        fun bind(photo: Photo) {
            binding.photo = photo // menyetel binding.property ke objek
            // Panggil executePendingBindings() agar update segera dijalankan
            binding.executePendingBindings() //
        }
    }

    /**
     *  Metode onCreateViewHolder()
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.PhotoViewHolder {
        // GridViewItemBinding yang menggunakan LayoutInflater dari konteks ViewGroup
        return PhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    /**
     * Method onBindViewHolder()
     */
    override fun onBindViewHolder(holder: PhotoGridAdapter.PhotoViewHolder,
                                  position: Int) {
        // mendapatkan objek Photo yang terkait dengan posisi RecyclerView saat ini
        val photo = getItem(position)
        holder.bind(photo)
    }

    /**
     * Membandingkan dua objek foto
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        /**
         * metode areItemsTheSame()
         * Metode ini dipanggil oleh DiffUtil untuk menentukan apakah dua objek
         * memiliki Item yang sama dengan mencocokan key Id dari kedua objek
         */
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            // mencocokan key id kedua objek apakah valuenya sama, lalu menampilkan hasilnya
            return oldItem.id == newItem.id
        }

        /**
         * Method areContentsTheSame()
         * Metode ini dipanggil oleh DiffUtil saat ingin memeriksa apakah dua item memiliki
         * data yang sama dengan mencocokan key download_url dari kedua objek.
         */
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            // mencocokan key download_url kedua objek apakah valuenya sama, lalu menampilkan hasilnya
            return oldItem.imgSrcUrl == newItem.imgSrcUrl //
        }
    }
}