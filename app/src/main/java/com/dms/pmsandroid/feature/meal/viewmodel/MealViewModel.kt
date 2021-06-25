package com.dms.pmsandroid.feature.meal.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.meal.MealApiImpl
import com.dms.pmsandroid.feature.meal.model.MealResponse
import java.time.format.DateTimeFormatter

class MealViewModel(
    private val mealApiImpl: MealApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val date = MutableLiveData<String>()

    private val _dayMeals = MutableLiveData<MealResponse>()
    val dayMeals : LiveData<MealResponse> get() = _dayMeals

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMeal() {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        date.value?.let {
            mealApiImpl.getMeal(accessToken, it.format(formatter)).subscribe({ response ->
                if(response.isSuccessful){
                    _dayMeals.value = response.body()
                }
            }, {
            })
        }
    }


}