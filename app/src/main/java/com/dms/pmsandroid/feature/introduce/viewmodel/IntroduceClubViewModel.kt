package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.feature.introduce.model.ClubModel

class IntroduceClubViewModel(private val introduceClubApiImpl: IntroduceClubApiImpl) : ViewModel() {
    val backClick = MutableLiveData<Boolean>(false)

    private val _clubs = MutableLiveData<List<ClubModel>>()
    val clubs: LiveData<List<ClubModel>> get() = _clubs

    fun backClicked() {
        backClick.value = true
    }

    fun loadClubs() {
    }


}