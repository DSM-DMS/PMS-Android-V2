package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.model.NoticeDetailModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class NoticeDetailViewModel(
    private val notifyApiImpl: NotifyApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _noticeDetail = MutableLiveData<NoticeDetailModel>()
    val noticeDetail: LiveData<NoticeDetailModel> get() = _noticeDetail

    val reComments = MutableLiveData(HashMap<Int,List<CommentModel>?>())

    val attachClicked = MutableLiveData(false)

    fun getNoticeDetail(id: Int) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.getNoticeDetail(accessToken, id).subscribe { response ->
            if (response.isSuccessful) {
                for(comments in response.body()!!.comment){
                    getReComments(accessToken,comments.id)
                }
                _noticeDetail.value = response.body()
            }
        }
    }

    private fun getReComments(accessToken:String,id:Int){
        notifyApiImpl.getReComments(accessToken,id).subscribe { response->
            if(response.isSuccessful){
                reComments.value!![id] = response.body()
            }
        }
    }

    fun onAttachClicked(){
        attachClicked.value = true
    }
}