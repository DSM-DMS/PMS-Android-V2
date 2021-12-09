package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.introduce.ProvideIntroduceClubApi
import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel

class IntroduceClubDetailViewModel(
    private val provideIntroduceClubApi: ProvideIntroduceClubApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _clubDetails = MutableLiveData<ClubDetailModel>()
    val clubDetail: LiveData<ClubDetailModel> get() = _clubDetails

    private val _close = MutableLiveData(false)
    val close: LiveData<Boolean> get() = _close


    fun loadClubDetail(clubname: String) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideIntroduceClubApi.clubDetailApi(accessToken, clubname).subscribe({
            if (it.isSuccessful) {
                _clubDetails.value = it.body()
            }
        }, {
        })
    }
}
