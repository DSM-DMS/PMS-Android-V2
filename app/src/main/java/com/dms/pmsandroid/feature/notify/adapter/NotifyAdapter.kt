package com.dms.pmsandroid.feature.notify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dms.pmsandroid.feature.notify.fragment.HomeNoticeFragment
import com.dms.pmsandroid.feature.notify.fragment.InNoticeFragment
import com.dms.pmsandroid.feature.notify.fragment.PhotoFragment

class NotifyAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->PhotoFragment()
            1->InNoticeFragment()
            else->HomeNoticeFragment()
        }
    }
}