package com.dms.pmsandroid.presentation.feature.introduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.introduce.remote.ProvideIntroduceClubApi
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.presentation.feature.introduce.model.ClubListModel

class IntroduceClubViewModel(
    private val provideIntroduceClubApi: ProvideIntroduceClubApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _clubs = MutableLiveData<ClubListModel>()
    val clubs: LiveData<ClubListModel> get() = _clubs

    private val _clickedClubId = MutableLiveData<String>()
    val clickedClubId : LiveData<String> get() = _clickedClubId

    fun onClubClicked(clubname :String){
        _clickedClubId.value = clubname
    }

    fun loadClubs() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideIntroduceClubApi.clubApi(accessToken).subscribe({
             if(it.isSuccessful){
                 _clubs.value = it.body()
             }
        }, {
        })
    }
}