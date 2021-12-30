package id.murdiantoroaji.ragil.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.murdiantoroaji.ragil.network.Api
import id.murdiantoroaji.ragil.network.Photo
import kotlinx.coroutines.launch

// mendeklarasikan class ApiStatus dengan type enum yang berisi LOADING, ERROR, dan DONE
enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
    // internal MutableLiveData yang menyimpan status dari request terakhir
    private val _status = MutableLiveData<ApiStatus>()

    // eksternal immutable LiveData yang digunakan untuk merequest status
    val status: LiveData<ApiStatus> = _status

    // Menyimpan objek foto
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    /**
     * memanggil fungsi getPhotos() di init
     * agar bisa menampilkan status immediately.
     */
    init {
        getPhotos()
    }

    /**
     * Mendapatkan informasi foto dari API Retrofit service
     */
    private fun getPhotos() {
        // Meluncurkan coroutine
        viewModelScope.launch {
            // Set _status.value menjdai Loading
            _status.value = ApiStatus.LOADING

            // Penanganan error handling dengan try catch
            try {
                 // Menyimpan data dari method getPhotos() pada object singleton Api
                _photos.value = Api.retrofitService.getPhotos()

                // menetapkan hasil yang diterima
                _status.value = ApiStatus.DONE
            } catch(e: Exception) {
                // menampilkan pesan error
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
