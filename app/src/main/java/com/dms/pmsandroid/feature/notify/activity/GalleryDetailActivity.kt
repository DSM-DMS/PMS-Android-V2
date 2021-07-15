package com.dms.pmsandroid.feature.notify.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityGalleryDetailBinding
import com.dms.pmsandroid.feature.notify.adapter.GalleryDetailAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.GalleryDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryDetailActivity :
    BaseActivity<ActivityGalleryDetailBinding>(R.layout.activity_gallery_detail) {

    override val vm: GalleryDetailViewModel by viewModel()

    private val detailAdapter : GalleryDetailAdapter by lazy {
        GalleryDetailAdapter(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id", 0)
        vm.loadGalleryDetail(id)
        val detailLayoutManager = LinearLayoutManager(binding.galleryDetailRv.context)
        detailLayoutManager.orientation=RecyclerView.VERTICAL
        binding.galleryDetailRv.run{
            adapter = detailAdapter
            layoutManager = detailLayoutManager
        }
    }

    override fun observeEvent() {
        vm.galleryDetail.observe(this,{
            val title = it.title
            val body = it.body
            val attach = it.attach
            detailAdapter.setGalleryDetail(attach,title, body)
        })
    }
}