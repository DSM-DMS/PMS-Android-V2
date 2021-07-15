package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.GalleryDetailModel

class GalleryDetailViewModel(private val notifyApiImpl: NotifyApiImpl):ViewModel() {

    private val _galleryDetail = MutableLiveData<GalleryDetailModel>()
    val galleryDetail: LiveData<GalleryDetailModel> get() = _galleryDetail

    fun loadGalleryDetail(id:Int){
        notifyApiImpl.getGalleryDetail(id).subscribe { response->
            if(response.isSuccessful){
                _galleryDetail.value = response.body()!!.gallery
            }
        }
    }
}