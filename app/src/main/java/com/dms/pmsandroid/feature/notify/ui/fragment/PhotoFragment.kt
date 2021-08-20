package com.dms.pmsandroid.feature.notify.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.FragmentPhotoBinding
import com.dms.pmsandroid.feature.notify.adapter.GalleryAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {
    override val vm: NotifyViewModel by sharedViewModel()

    private val photoAdapter: GalleryAdapter by lazy {
        GalleryAdapter(vm)
    }

    private val photoLayoutManager:LinearLayoutManager by lazy {
        LinearLayoutManager(binding.photoRv.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getGalleryList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        photoLayoutManager.orientation = RecyclerView.VERTICAL
        binding.photoRv.run {
            layoutManager = photoLayoutManager
            adapter = photoAdapter

        }
    }

    override fun observeEvent() {
        vm.run {
            galleryList.observe(viewLifecycleOwner, {
                photoAdapter.setItems(it)
            })
            clickedGalleryId.observe(viewLifecycleOwner, EventObserver{
                (activity as MainActivity).startGalleryDetail(it)
            })
            galleryPage.observe(viewLifecycleOwner,{
                photoLayoutManager.scrollToPositionWithOffset(0,0)
            })
        }
    }
}
