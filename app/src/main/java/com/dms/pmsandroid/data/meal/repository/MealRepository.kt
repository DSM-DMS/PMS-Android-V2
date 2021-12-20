package com.dms.pmsandroid.data.meal.repository

import com.dms.pmsandroid.feature.meal.entity.MealItem
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface MealRepository {

    fun getYesterdayTodayTomorrowMeals(currentDate: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>>

    fun getOneDayMeal(date: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>>
}