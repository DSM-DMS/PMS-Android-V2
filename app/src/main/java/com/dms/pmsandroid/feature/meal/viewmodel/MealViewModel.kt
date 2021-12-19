package com.dms.pmsandroid.feature.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.meal.repository.MealRepository
import com.dms.pmsandroid.feature.meal.entity.MealItem
import java.time.LocalDate
import kotlin.collections.HashMap

class MealViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    val selectedDate = MutableLiveData(LocalDate.now())
    val currentPosition = MutableLiveData(Int.MAX_VALUE / 2)

    private val _showPicture = MutableLiveData(false)
    val showPicture: LiveData<Boolean> get() = _showPicture

    private val _meals = MutableLiveData(HashMap<Int, MealItem>())
    val meals: LiveData<HashMap<Int, MealItem>> get() = _meals

    fun clickNextDay() {
        movePositionToNextDayMorning()
        nextDay()
    }

    fun nextDay() {
        plusOneToPosition()
        loadNextDayMeal()
    }

    fun clickBeforeDay() {
        movePositionToBeforeDayMorning()
        beforeDay()
    }

    fun beforeDay() {
        minusOneToPosition()
        loadBeforeDayMeal()
    }

    private fun movePositionToNextDayMorning() {
        currentPosition.value = currentPosition.value!! + (2 - (currentPosition.value!! % 3))
    }

    private fun movePositionToBeforeDayMorning() {
        currentPosition.value = currentPosition.value!! - (currentPosition.value!! % 3)
    }

    private fun plusOneToPosition() {
        currentPosition.value = currentPosition.value!! + 1
    }

    private fun minusOneToPosition() {
        currentPosition.value = currentPosition.value!! - 1
    }


    private fun loadNextDayMeal() {
        val position = currentPosition.value!!

        if(positionIsEmpty(position)) {
            appendMealItems(position)
        }
    }

    private fun loadBeforeDayMeal() {
        val position = currentPosition.value!! - 2

        if(positionIsEmpty(position)) {
            appendMealItems(position)
        }
    }

    private fun appendMealItems(position: Int) {
        mealRepository.getOneDayMeal(selectedDate.value!!, position).subscribe { result ->
            _meals.value!!.putAll(result)
        }
    }

    private fun positionIsEmpty(position: Int): Boolean = !meals.value!!.containsKey(position)

    fun getInitMeal() {
        mealRepository.getYesterdayTodayTomorrowMeals(selectedDate.value!!, currentPosition.value!!).subscribe { result ->
            _meals.value = result
        }
    }

    fun showPicture(hasPicture: Boolean) {
        if (hasPicture) {
            _showPicture.value = !showPicture.value!!
        }
    }

}