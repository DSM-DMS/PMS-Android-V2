package com.dms.pmsandroid.domain.meal.repository

import com.dms.pmsandroid.presentation.feature.meal.entity.MealItem
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface MealRepository {

    fun getYesterdayTodayTomorrowMeals(currentDate: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>>

    fun getOneDayMeal(date: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>>
}