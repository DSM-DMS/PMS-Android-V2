package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.GalleryListContent
import com.dms.pmsandroid.feature.notify.model.GalleryListModel

class NotifyViewModel(private val notifyApiImpl: NotifyApiImpl) : ViewModel() {
    private val _galleryPage = MutableLiveData(1)
    val galleryPage: LiveData<Int> get() = _galleryPage

    private val _galleryList = MutableLiveData<List<GalleryListContent>>()
    val galleryList: LiveData<List<GalleryListContent>> get() = _galleryList

    var totalLength = 1

    fun getGalleryList() {
        notifyApiImpl.getGalleryList(galleryPage.value!! - 1, 6).subscribe({
            if (it.isSuccessful) {
                _galleryList.value = it.body()!!.galleries
                totalLength = (it.body()!!.totalLength / 6)+1
            }
        }, {
        })
    }

    fun galleryBeforePage() {
        if (galleryPage.value!! > 1) {
            _galleryPage.value = _galleryPage.value!! - 1
            getGalleryList()
        }
    }

    fun galleryAfterPage() {
        if (galleryPage.value!! < totalLength) {
            _galleryPage.value = _galleryPage.value!! + 1
            getGalleryList()
        }

    }
}