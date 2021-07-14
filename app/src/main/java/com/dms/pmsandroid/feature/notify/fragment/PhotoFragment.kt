package com.dms.pmsandroid.feature.notify.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentPhotoBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {
    override val vm: NotifyViewModel by sharedViewModel()

    override fun observeEvent() {

    }
}