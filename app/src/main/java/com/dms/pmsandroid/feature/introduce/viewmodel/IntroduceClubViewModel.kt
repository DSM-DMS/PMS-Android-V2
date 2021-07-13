package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.feature.introduce.model.ClubModel

class IntroduceClubViewModel(
    private val introduceClubApiImpl: IntroduceClubApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _clubs = MutableLiveData<List<ClubModel>>()
    val clubs: LiveData<List<ClubModel>> get() = _clubs


    fun loadClubs() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        introduceClubApiImpl.clubApi(accessToken).subscribe({
             if(it.isSuccessful){
                 _clubs.value = it.body()
             }
        }, {

        })
    }


}