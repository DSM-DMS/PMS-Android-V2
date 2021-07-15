package com.dms.pmsandroid.feature.notify.activity

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityGalleryDetailBinding
import com.dms.pmsandroid.feature.notify.viewmodel.GalleryDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryDetailActivity : BaseActivity<ActivityGalleryDetailBinding>(R.layout.activity_gallery_detail) {
    override val vm:GalleryDetailViewModel by viewModel()

    override fun observeEvent() {

    }
}