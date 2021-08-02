package com.dms.pmsandroid.feature.notify.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.model.CommentRequestModel
import com.dms.pmsandroid.feature.notify.model.NoticeDetailModel

class NoticeDetailViewModel(
    private val notifyApiImpl: NotifyApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _noticeDetail = MutableLiveData<NoticeDetailModel>()
    val noticeDetail: LiveData<NoticeDetailModel> get() = _noticeDetail

    val comment = MutableLiveData<String>()

    val reComments = MutableLiveData(HashMap<Int, List<CommentModel>?>())

    val doneReComments = MutableLiveData(false)

    val attachClicked = MutableLiveData(false)

    private val _resetComments = MutableLiveData(false)
    val resetComments: LiveData<Boolean> get() = _resetComments

    private val _clickedCommentId = MutableLiveData<Int>()
    val clickedCommentId:LiveData<Int> get() = _clickedCommentId

    var noticeId = -1

    fun getNoticeDetail(id: Int) {
        noticeId = id
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.getNoticeDetail(accessToken, id).subscribe { response ->
            if (response.isSuccessful) {
                getReComments(accessToken, response.body()!!.comment)
                _noticeDetail.value = response.body()
            }
        }
    }

    private fun getReComments(accessToken: String, comments: List<CommentModel>) {
        for (comment in comments) {
            val id = comment.id
            notifyApiImpl.getReComments(accessToken, id).subscribe { response ->
                if (response.isSuccessful) {
                    reComments.value!![id] = response.body()
                }
                doneReComments.value = true
            }
        }
    }

    fun postComment() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val body = CommentRequestModel(comment.value!!,clickedCommentId.value)
        notifyApiImpl.postComment(accessToken,noticeId, body).subscribe { response ->
            if (response.isSuccessful) {
                _resetComments.value = true
                comment.value = null
                getNoticeDetail(noticeId)
            }
        }
    }

    fun commentClick(id:Int){
        _clickedCommentId.value = id
    }

    fun onAttachClicked() {
        attachClicked.value = true
    }
}