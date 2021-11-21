package com.dms.pmsandroid.feature.meal.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.meal.ProvideMealApi
import com.dms.pmsandroid.feature.meal.entity.MealItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate
import kotlin.collections.HashMap

class MealViewModel(
    private val provideMealApi: ProvideMealApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val date = MutableLiveData(LocalDate.now())

    val currentPosition = MutableLiveData(Int.MAX_VALUE / 2)

    private val _showPicture = MutableLiveData(false)
    val showPicture: LiveData<Boolean> get() = _showPicture

    private val _meals = MutableLiveData(HashMap<Int, MealItem>())
    val meals: LiveData<HashMap<Int, MealItem>> get() = _meals

    @RequiresApi(Build.VERSION_CODES.O)
    fun clickNextDay() {
        currentPosition.value = currentPosition.value!! + (2 - (currentPosition.value!! % 3))
        nextDay()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextDay() {
        currentPosition.value = currentPosition.value!! + 1
        date.value = date.value!!.plusDays(1)
        loadNextDayMeal()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun clickBeforeDay() {
        currentPosition.value = currentPosition.value!! - (currentPosition.value!! % 3)
        beforeDay()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun beforeDay() {
        currentPosition.value = currentPosition.value!! - 1
        date.value = date.value!!.minusDays(1)
        loadBeforeDayMeal()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNextDayMeal() {
        getMeal(isBeforeDay = false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadBeforeDayMeal() {
        getMeal(isBeforeDay = true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMeal(isBeforeDay: Boolean) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val position = if(isBeforeDay) currentPosition.value!! - 2 else currentPosition.value!!

        if(!meals.value!!.containsKey(position)) {
            provideMealApi.getMealItem(accessToken, date.value!!.toDateTimeString(), position)
                .subscribe { meal ->
                    _meals.value = meals.value!!.apply {
                        putAll(meal)
                    }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getInitMeal() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")

        val currentDate = date.value!!
        val nextDate = currentDate.plusDays(1)
        val beforeDate = currentDate.minusDays(1)

        val todayMeal = provideMealApi.getMealItem(
            accessToken,
            currentDate.toDateTimeString(),
            currentPosition.value!!
        )
        val nextDayMeal = provideMealApi.getMealItem(
            accessToken,
            nextDate.toDateTimeString(),
            currentPosition.value!! + 3
        )
        val lastDayMeal = provideMealApi.getMealItem(
            accessToken,
            beforeDate.toDateTimeString(),
            currentPosition.value!! - 3
        )
        Single.zip(
            todayMeal,
            nextDayMeal,
            lastDayMeal,
            { t1, t2, t3 ->
                HashMap<Int, MealItem>().apply {
                    putAll(t1)
                    putAll(t2)
                    putAll(t3)
                }
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { mealList ->
                _meals.value = mealList
            }
    }



    fun showPicture(hasPicture: Boolean) {
        if (hasPicture) {
            _showPicture.value = !showPicture.value!!
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun LocalDate.toDateTimeString() =
        "${this.year}${String.format("%02d", this.monthValue)}${String.format("%02d", this.dayOfMonth)}"

}