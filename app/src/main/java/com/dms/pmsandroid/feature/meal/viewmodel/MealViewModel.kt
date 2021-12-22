package com.dms.pmsandroid.feature.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.meal.repository.MealRepository
import com.dms.pmsandroid.feature.meal.entity.MealItem
import com.dms.pmsandroid.feature.meal.entity.NeedUpdateMealItems
import java.time.LocalDate
import kotlin.collections.HashMap

class MealViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    val selectedDate = MutableLiveData(LocalDate.now())
    val currentPosition = MutableLiveData(Int.MAX_VALUE / 2)

    private val _showPicturePosition = MutableLiveData<Int>()
    val showPicturePosition: LiveData<Int> get() = _showPicturePosition

    private val _meals = MutableLiveData(HashMap<Int, MealItem>())
    val meals: LiveData<HashMap<Int, MealItem>> get() = _meals

    private val _showingPicturePositions = HashMap<Int, Boolean>()
    val showingPicturePositions: HashMap<Int, Boolean> get() = _showingPicturePositions

    private val _needUpdateMealItemsPosition = SingleLiveEvent<NeedUpdateMealItems>()
    val needUpdateMealItems: LiveData<NeedUpdateMealItems> get() = _needUpdateMealItemsPosition

    fun clickNextDay() {
        movePositionToNextDayMorning()
        nextDay()
    }

    fun clickBeforeDay() {
        movePositionToBeforeDayDinner()
        beforeDay()
    }

    fun nextDay() {
        plusOneToPosition()
        plusOneDay()
        loadNextDayMeal()
    }

    fun beforeDay() {
        minusOneToPosition()
        minusOneDay()
        loadBeforeDayMeal()
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
            _needUpdateMealItemsPosition.value = NeedUpdateMealItems(position, result)
        }
    }

    private fun positionIsEmpty(position: Int): Boolean = !meals.value!!.containsKey(position)

    fun getInitMeal() {
        val internetDisConnectedComment = "인터넷 연결상태를 확인해주세요."
        val internetDisConnectedItem = MealItem(internetDisConnectedComment, null, false)
        mealRepository.getYesterdayTodayTomorrowMeals(selectedDate.value!!, currentPosition.value!!).onErrorReturn {
            val pleaseCheckInternetItems = HashMap<Int, MealItem>()
            pleaseCheckInternetItems[currentPosition.value!!] = internetDisConnectedItem
            pleaseCheckInternetItems[currentPosition.value!! + 1] = internetDisConnectedItem
            pleaseCheckInternetItems[currentPosition.value!! - 1] = internetDisConnectedItem
            pleaseCheckInternetItems
        }.subscribe { result ->
            _meals.value = result
        }
    }

    fun showPicture(hasPicture: Boolean, position: Int) {
        if (hasPicture) {
            _showingPicturePositions[position] = !(showingPicturePositions[position]?:false)
            _showPicturePosition.value = position
        }
    }

    private fun plusOneDay() {
        selectedDate.value = selectedDate.value!!.plusDays(1)
    }

    private fun minusOneDay() {
        selectedDate.value = selectedDate.value!!.minusDays(1)
    }

    private fun movePositionToNextDayMorning() {
        currentPosition.value = currentPosition.value!! + (2 - (currentPosition.value!! % 3))
    }

    private fun movePositionToBeforeDayDinner() {
        currentPosition.value = currentPosition.value!! - (currentPosition.value!! % 3)
    }

    private fun plusOneToPosition() {
        currentPosition.value = currentPosition.value!! + 1
    }

    private fun minusOneToPosition() {
        currentPosition.value = currentPosition.value!! - 1
    }
}