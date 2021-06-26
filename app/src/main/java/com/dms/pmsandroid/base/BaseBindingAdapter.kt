package com.dms.pmsandroid.base

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object BaseBindingAdapter {
    @JvmStatic
    @BindingAdapter("recycler_view_adapter")
    fun recyclerViewAdapter(recyclerView: RecyclerView,adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>?){
        val setLayoutManager = LinearLayoutManager(recyclerView.context)
        setLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = setLayoutManager
        if(adapter!=null){
            recyclerView.adapter = adapter
        }

    }
}