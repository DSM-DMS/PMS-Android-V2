package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.GalleryListContent
import com.dms.pmsandroid.feature.notify.model.NoticeResponseModel

class NotifyViewModel(
    private val notifyApiImpl: NotifyApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    private val _galleryPage = MutableLiveData(1)
    val galleryPage: LiveData<Int> get() = _galleryPage

    private val _noticePage = MutableLiveData(1)
    val noticePage: LiveData<Int> get() = _noticePage

    private val _homePage = MutableLiveData(1)
    val homePage: LiveData<Int> get() = _homePage

    private val _galleryList = MutableLiveData<List<GalleryListContent>>()
    val galleryList: LiveData<List<GalleryListContent>> get() = _galleryList

    private val _noticeList = MutableLiveData<NoticeResponseModel>()
    val noticeList: LiveData<NoticeResponseModel> get() = _noticeList

    private val _homeList = MutableLiveData<NoticeResponseModel>()
    val homeList: LiveData<NoticeResponseModel> get() = _homeList

    private val _clickedGalleryId = MutableLiveData<Event<Int>>()
    val clickedGalleryId: LiveData<Event<Int>> get() = _clickedGalleryId

    private val _clickedNoticeId = MutableLiveData<Event<Int>>()
    val clickedNoticeId: LiveData<Event<Int>> get() = _clickedNoticeId

    lateinit var clickedNoticeTitle: String

    var galleryTotalPage = MutableLiveData(1)


    val needDownLoad = MutableLiveData(false)

    fun getGalleryList() {
        notifyApiImpl.getGalleryList(galleryPage.value!! - 1, 6).subscribe({
            if (it.isSuccessful) {
                _galleryList.value = it.body()!!.galleries
                galleryTotalPage.value = it.body()!!.totalPage
            }
        }, {
        })
    }

    fun getNoticeList(next: Int) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.getNoticeList(accessToken, (noticePage.value!! + next) - 1, 6)
            .subscribe { response ->
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (next > 0) {
                            _noticePage.value = _noticePage.value!! + 1
                        }
                        _noticeList.value = response.body()
                    }
                }
            }
    }

    fun getHomeNoticeList(next: Int) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.getHomeNoticeList(accessToken, (homePage.value!! + next) - 1, 6)
            .subscribe { response ->
                if (response.isSuccessful) {
                    if (response.body()!!.notices.isNotEmpty()) {
                        if (next > 0) {
                            _homePage.value = _homePage.value!! + 1
                        }
                        _homeList.value = response.body()
                    }
                }
            }
    }

    fun searchNotice(keyword: String) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.searchNotice(accessToken, keyword, 0)
            .subscribe { response ->
                if (response.isSuccessful) {
                    _noticeList.value = response.body()
                }
            }
    }


    fun searchHome(keyword: String) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.searchHome(accessToken, keyword, 0)
            .subscribe { response ->
                if (response.isSuccessful) {
                    _homeList.value = response.body()
                }
            }
    }

    fun galleryBeforePage() {
        _galleryPage.value = _galleryPage.value!! - 1
        getGalleryList()
    }

    fun galleryAfterPage() {
        _galleryPage.value = galleryPage.value!! + 1
        getGalleryList()
    }

    fun resetNoticePage() {
        _noticePage.value = 1
    }

    fun noticeBeforePage() {
        if (noticePage.value!! > 1) {
            _noticePage.value = _noticePage.value!! - 1
            getNoticeList(0)
        }
    }


    fun noticeAfterPage() {
        getNoticeList(1)
    }

    fun homeBeforePage() {
        if (homePage.value!! > 1) {
            _homePage.value = _homePage.value!! - 1
            getHomeNoticeList(0)
        }
    }

    fun homeAfterPage() {
        getHomeNoticeList(1)
    }

    fun resetHomePage() {
        _homePage.value = 1
    }

    fun onGalleryClicked(id: Int) {
        _clickedGalleryId.value = Event(id)
    }

    fun onNoticeClicked(id: Int, title: String) {
        clickedNoticeTitle = title
        _clickedNoticeId.value = Event(id)
    }
}