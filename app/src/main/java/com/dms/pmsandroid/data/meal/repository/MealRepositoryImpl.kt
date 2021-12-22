package com.dms.pmsandroid.data.meal.repository

import com.dms.pmsandroid.data.meal.remote.ProvideMealApi
import com.dms.pmsandroid.feature.meal.entity.MealItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate
import kotlin.collections.HashMap

class MealRepositoryImpl(
    private val provideMealApi: ProvideMealApi
) : MealRepository {

    override fun getYesterdayTodayTomorrowMeals(currentDate: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>> {
        val yesterdayDate = currentDate.minusDays(1)
        val tomorrowDate = currentDate.plusDays(1)

        val yesterdayMealItemObservable = provideMealApi.getMealItem(yesterdayDate, yesterdayPosition(currentPosition))
        val todayMealItemObservable = provideMealApi.getMealItem(currentDate, currentPosition)
        val tomorrowMealItemObservable = provideMealApi.getMealItem(tomorrowDate, tomorrowPosition(currentPosition))
        
        return Single.zip(
            todayMealItemObservable,
            tomorrowMealItemObservable,
            yesterdayMealItemObservable,
            { todayMealItems, tomorrowMealItems, yesterdayMealItems ->
                HashMap<Int, MealItem>().apply {
                    putAll(todayMealItems)
                    putAll(tomorrowMealItems)
                    putAll(yesterdayMealItems)
                }
            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    private fun yesterdayPosition(currentPosition: Int) = currentPosition - 3

    private fun tomorrowPosition(currentPosition: Int) = currentPosition + 3


    override fun getOneDayMeal(date: LocalDate, currentPosition: Int): Single<HashMap<Int, MealItem>> =
        provideMealApi.getMealItem(date, currentPosition)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


}