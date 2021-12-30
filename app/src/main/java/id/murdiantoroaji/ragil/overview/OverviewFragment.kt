package id.murdiantoroaji.ragil.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.murdiantoroaji.ragil.databinding.FragmentOverviewBinding
import id.murdiantoroaji.ragil.overview.OverviewViewModel

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

    /**
     * Mengisi layout dengan data binding, set lingkungan ke OverviewFragment
     * untuk meng aktifkan data binding ke LiveData,
     * dan menyalakan RecyclerView dengan sebuah adapter
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // meng-inflate class FragmentOverviewBinding
        val binding = FragmentOverviewBinding.inflate(inflater)

        // mengizinthikan data binding ke liveData dengan lingkungan dari fragment this
        binding.lifecycleOwner = this

        // memberikan binding access ke OverviewViewModel
        binding.viewModel = viewModel

        // set adapter dari photosGrid RecycleView
        binding.photosGrid.adapter = PhotoGridAdapter()

        // mengembalikan binding.root
        return binding.root
    }
}
