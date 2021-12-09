package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notify.ProvideNotifyApi
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.model.CommentRequestModel
import com.dms.pmsandroid.feature.notify.model.NoticeDetailModel

class NoticeDetailViewModel(
    private val provideNotifyApi: ProvideNotifyApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _noticeDetail = MutableLiveData<NoticeDetailModel>()
    val noticeDetail: LiveData<NoticeDetailModel> get() = _noticeDetail

    val comment = MutableLiveData<String>()

    val reComments = MutableLiveData(HashMap<Int, List<CommentModel>?>())

    val doneReComments = MutableLiveData<Int>()

    private val _resetComments = MutableLiveData(Event(false))
    val resetComments: LiveData<Event<Boolean>> get() = _resetComments

    private val _clickedCommentId = MutableLiveData<Event<Int>>()
    val clickedCommentId: LiveData<Event<Int>> get() = _clickedCommentId

    var noticeId = -1

    fun loadNoticeDetail() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideNotifyApi.getNoticeDetail(accessToken, noticeId).subscribe { response ->
            if (response.isSuccessful) {
                getReComments(accessToken, response.body()!!.comment)
                _noticeDetail.value = response.body()
            }
        }
    }

    private fun getReComments(accessToken: String, comments: List<CommentModel>) {
        var position = 1
        for (comment in comments) {
            val id = comment.id
            provideNotifyApi.getReComments(accessToken, id).subscribe { response ->
                if (response.isSuccessful) {
                    reComments.value!![id] = response.body()
                }
                doneReComments.value = position
                position += 1
            }
        }
    }

    fun postComment() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val body = CommentRequestModel(comment.value!!, clickedCommentId.value?.peekContent())
        if (!comment.value.isNullOrEmpty()) {
            provideNotifyApi.postComment(accessToken, noticeId, body).subscribe { response ->
                if (response.isSuccessful) {
                    _resetComments.value = Event(true)
                    loadNoticeDetail()
                }
            }
        }
    }

    fun commentClick(id: Int) {
        _clickedCommentId.value = Event(id)
    }
}