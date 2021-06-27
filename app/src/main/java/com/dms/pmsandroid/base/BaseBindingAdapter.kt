package com.dms.pmsandroid.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dms.pmsandroid.R

object BaseBindingAdapter {
    @JvmStatic
    @BindingAdapter("recycler_view_adapter")
    fun recyclerViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
    ) {
        val setLayoutManager = LinearLayoutManager(recyclerView.context)
        setLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = setLayoutManager
        if (adapter != null) {
            recyclerView.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("glide_image_load")
    fun glideImageLoad(imageView: ImageView, resource: String?) {
        Glide.with(imageView.context)
            .load(resource)
            .error(R.drawable.img_no_picture)
            .into(imageView)
    }
}