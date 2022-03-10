package com.dms.pmsandroid.presentation.feature.notify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dms.pmsandroid.presentation.feature.notify.ui.fragment.HomeNoticeFragment
import com.dms.pmsandroid.presentation.feature.notify.ui.fragment.InNoticeFragment
import com.dms.pmsandroid.presentation.feature.notify.ui.fragment.GalleryFragment

class NotifyAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->GalleryFragment()
            1->InNoticeFragment()
            else->HomeNoticeFragment()
        }
    }
}