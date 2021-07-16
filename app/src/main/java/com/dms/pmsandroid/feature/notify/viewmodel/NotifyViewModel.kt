package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.GalleryListContent

class NotifyViewModel(private val notifyApiImpl: NotifyApiImpl) : ViewModel() {
    private val _galleryPage = MutableLiveData(1)
    val galleryPage: LiveData<Int> get() = _galleryPage

    private val _noticePage = MutableLiveData(1)
    val noticePage: LiveData<Int> get() = _noticePage

    private val _galleryList = MutableLiveData<List<GalleryListContent>>()
    val galleryList: LiveData<List<GalleryListContent>> get() = _galleryList

    private val _clickedGalleryId = MutableLiveData<Int>()
    val clickedGalleryId: LiveData<Int> get() = _clickedGalleryId

    private val _clickedNoticeId = MutableLiveData<Int>()
    val clickedNoticeId: LiveData<Int> get() = _clickedNoticeId

    private var galleryTotalLength = 1
    private var noticeTotalLength = 1

    fun getGalleryList() {
        notifyApiImpl.getGalleryList(galleryPage.value!! - 1, 6).subscribe({
            if (it.isSuccessful) {
                _galleryList.value = it.body()!!.galleries
                galleryTotalLength = (it.body()!!.totalLength / 6) + 1
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
        if (galleryPage.value!! < galleryTotalLength) {
            _galleryPage.value = _galleryPage.value!! + 1
            getGalleryList()
        }
    }

    fun noticeBeforePage(){
        if(noticePage.value!!>1){
            _noticePage.value = _noticePage.value!! - 1
        }
    }

    fun noticeAfterPage(){
        if(noticePage.value!!>noticeTotalLength){
            _noticePage.value = _noticePage.value!! + 1
        }
    }

    fun onGalleryClicked(id: Int) {
        _clickedGalleryId.value = id
    }

    fun onNoticeClicked(id:Int){
        _clickedNoticeId.value = id
    }
}