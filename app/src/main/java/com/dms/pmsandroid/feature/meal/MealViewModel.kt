package com.dms.pmsandroid.feature.meal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.meal.MealApiImpl
import com.dms.pmsandroid.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.feature.meal.model.MealResponse
import java.time.format.DateTimeFormatter

class MealViewModel(
    private val mealApiImpl: MealApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val date = MutableLiveData<String>()

    val weekDate = MutableLiveData<Int>()

    private val _showPicture = MutableLiveData(false)
    val showPicture:LiveData<Boolean> get() = _showPicture

    private val _meals = MutableLiveData<MealResponse>()
    val meals: LiveData<MealResponse> get() = _meals

    private val _mealsPicture = MutableLiveData<MealPictureResponse>()
    val mealPicture: LiveData<MealPictureResponse> get() = _mealsPicture

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMeal() {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val date = date.value?.format(formatter) ?: ""
        mealApiImpl.getMeal(accessToken, date).subscribe({ response ->
            response.raw()
            if (response.isSuccessful) {
                _meals.value = response.body()
            } else {
                _meals.value = MealResponse(null, null, null)
            }
        }, {
            _meals.value = MealResponse(null, null, null)
        })
        getMealPicture(accessToken, date)
    }

    private fun getMealPicture(accessToken: String, date: String) {
        mealApiImpl.getMealPicture(accessToken, date).subscribe({
            if(it.isSuccessful){
                _mealsPicture.value = it.body()
            }else{
                _mealsPicture.value = MealPictureResponse(null,null,null)
            }
        }, {
            _mealsPicture.value = MealPictureResponse(null,null,null)
        })
    }

    fun showPicture(){
        _showPicture.value = !_showPicture.value!!
    }


}