package com.dms.pmsandroid.feature.meal.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.meal.ProvideMealApi
import com.dms.pmsandroid.feature.meal.entity.Meal
import com.dms.pmsandroid.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.feature.meal.model.MealResponse
import com.dms.pmsandroid.feature.meal.model.toEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MealViewModel(
    private val provideMealApi: ProvideMealApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val date = MutableLiveData(LocalDate.now())

    val currentPosition = MutableLiveData(Int.MAX_VALUE / 2)

    private val _showPicture = MutableLiveData(false)
    val showPicture:LiveData<Boolean> get() = _showPicture

    private val _meals = MutableLiveData<Meal>()
    val meals: LiveData<Meal> get() = _meals

    private val _mealsPicture = MutableLiveData<MealPictureResponse>()
    val mealPicture: LiveData<MealPictureResponse> get() = _mealsPicture

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMeal() {

    }

    private fun getMealPicture(accessToken: String, date: String) {
        provideMealApi.getMealPicture(accessToken, date).subscribe({
            if(it.isSuccessful){
                _mealsPicture.value = it.body()
            }else{
                _mealsPicture.value = MealPictureResponse(null,null,null)
            }
        }, {
            _mealsPicture.value = MealPictureResponse(null,null,null)
        })
    }

    fun showPicture(hasPicture:Boolean){
        if(hasPicture){
            _showPicture.value = !_showPicture.value!!
        }
    }


}