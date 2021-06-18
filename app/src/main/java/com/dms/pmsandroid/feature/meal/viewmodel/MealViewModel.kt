package com.dms.pmsandroid.feature.meal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.meal.MealApiImpl

class MealViewModel(
    private val mealApiImpl: MealApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    fun getMeal(dateTime: String) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        mealApiImpl.getMeal(accessToken, dateTime).subscribe({ response ->
            Log.d("결과",response.raw().toString())
        }, {
            Log.d("결과",it.toString())
        })
    }


}