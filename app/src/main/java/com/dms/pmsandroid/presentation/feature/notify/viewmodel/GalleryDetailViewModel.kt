package com.dms.pmsandroid.presentation.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.notify.ProvideNotifyApi
import com.dms.pmsandroid.presentation.feature.notify.model.GalleryDetailModel

class GalleryDetailViewModel(private val provideNotifyApi: ProvideNotifyApi) : ViewModel() {

    private val _galleryDetail = MutableLiveData<GalleryDetailModel>()
    val galleryDetail: LiveData<GalleryDetailModel> get() = _galleryDetail

    private val _close = MutableLiveData(false)
    val close: LiveData<Boolean> get() = _close

    fun loadGalleryDetail(id: Int) {
        provideNotifyApi.getGalleryDetail(id).subscribe { response ->
            if (response.isSuccessful) {
                _galleryDetail.value = response.body()!!.gallery
            }
        }
    }

    fun close() {
        _close.value = true
    }
}