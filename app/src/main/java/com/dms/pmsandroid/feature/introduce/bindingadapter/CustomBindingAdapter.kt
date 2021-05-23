package com.dms.pmsandroid.feature.introduce.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.feature.introduce.model.DevelopModel

object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter("posts")
    fun setBindPost(view: RecyclerView, posts: LiveData<ArrayList<DevelopModel>>){
        view.adapter?.run{
            if(this is developListAdapter){
                posts.value?.let { this.posts = it }?:{this.posts = arrayListOf () }()
                this.notifyDataSetChanged()

            }
        }
    }
}