package com.dms.pmsandroid.data.meal.remote

import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.dms.pmsandroid.feature.meal.entity.MealItem
import com.dms.pmsandroid.feature.meal.entity.toMealText
import com.dms.pmsandroid.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.feature.meal.model.MealResponse
import com.dms.pmsandroid.feature.meal.model.toEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class ProvideMealApi(api: PotatoChipApi) {

    private val mealApi = api.retrofit.create(MealApi::class.java)

    private fun getMeal(accessToken: String, dateTime: String): @NonNull Single<Response<MealResponse>> =
        mealApi.getMeal(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    private fun getMealPicture(
        accessToken: String,
        dateTime: String
    ): @NonNull Single<Response<MealPictureResponse>> =
        mealApi.getMealPicture(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMealItem(accessToken: String, dateTime: String, currentPosition: Int): @NonNull Single<HashMap<Int, MealItem>> =
        Single.zip(getMeal(accessToken, dateTime),getMealPicture(accessToken, dateTime), BiFunction{t1, t2 ->
            if(t1.isSuccessful){
                if(t1.body() != null) {
                    return@BiFunction HashMap<Int, MealItem>().apply {
                        put(currentPosition, MealItem(t1.body()!!.toEntity().breakfast.toMealText(), t2.body()?.breakfast))
                        put(currentPosition + 1, MealItem(t1.body()!!.toEntity().lunch.toMealText(), t2.body()?.lunch))
                        put(currentPosition + 2, MealItem(t1.body()!!.toEntity().dinner.toMealText(),t2.body()?.dinner))
                    }
                } else {
                    return@BiFunction HashMap<Int, MealItem>()
                }
            }else {
                return@BiFunction HashMap<Int, MealItem>()
            }
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}