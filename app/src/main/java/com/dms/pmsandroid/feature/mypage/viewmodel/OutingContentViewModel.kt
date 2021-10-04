package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.ProvideMyPageApi
import com.dms.pmsandroid.feature.mypage.model.OutingListResponse

class OutingContentViewModel(
    private val provideMyPageApi: ProvideMyPageApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _outing = MutableLiveData<OutingListResponse>()
    val outings: MutableLiveData<OutingListResponse> get() = _outing

    fun loadOuting(number: Int) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideMyPageApi.getOutingApi(accessToken, number).subscribe({
            if (it.isSuccessful) {
                _outing.value = it.body()
            }
        }, {
        })
    }
}