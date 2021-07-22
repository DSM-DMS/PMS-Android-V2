package com.dms.pmsandroid.feature.introduce.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel

class IntroduceClubDetailViewModel(
    private val introduceClubApiImpl: IntroduceClubApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _clubDetails = MutableLiveData<ClubDetailModel>()
    val clubDetail : LiveData<ClubDetailModel> get() = _clubDetails

    fun loadClubDetail() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        introduceClubApiImpl.clubDetailApi(accessToken,"clubName").subscribe({
            if (it.isSuccessful) {
                _clubDetails.value = it.body()
                Log.e("오류", "오류")
            }
        }, {
        })

    }
}