package com.dms.pmsandroid.data.meal.remote

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.dms.pmsandroid.presentation.feature.meal.entity.MealItem
import com.dms.pmsandroid.presentation.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.presentation.feature.meal.model.MealResponse
import com.dms.pmsandroid.presentation.feature.meal.model.toEntity
import com.dms.pmsandroid.presentation.feature.meal.toMealText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

class ProvideMealApi(api: PotatoChipApi, private val sharedPreferenceStorage: SharedPreferenceStorage) {

    private val mealApi = api.retrofit.create(MealApi::class.java)

    private val accessToken by lazy {
        sharedPreferenceStorage.getInfo("access_token")
    }

    private fun getMeal(dateTime: String): @NonNull Single<Response<MealResponse>> =
        mealApi.getMeal(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    private fun getMealPicture(
        dateTime: String
    ): @NonNull Single<Response<MealPictureResponse>> =
        mealApi.getMealPicture(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMealItem(dateTime: LocalDate, currentPosition: Int): @NonNull Single<HashMap<Int, MealItem>> =
        Single.zip(getMeal(dateTime.toDateTimeString()),getMealPicture(dateTime.toDateTimeString()), BiFunction{response, t2 ->
            if(response.isSuccessful && response.body() != null){
                    return@BiFunction HashMap<Int, MealItem>().apply {
                        put(
                            currentPosition,
                            MealItem(
                                response.body()!!.toEntity().breakfast.toMealText(),
                                t2.body()?.breakfast
                            )
                        )
                        put(
                            currentPosition + 1,
                            MealItem(response.body()!!.toEntity().lunch.toMealText(), t2.body()?.lunch)
                        )
                        put(
                            currentPosition + 2,
                            MealItem(response.body()!!.toEntity().dinner.toMealText(), t2.body()?.dinner)
                        )
                    }
            } else {
                return@BiFunction HashMap<Int, MealItem>()
            }
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    private fun LocalDate.toDateTimeString() =
        "${this.year}${String.format("%02d", this.monthValue)}${String.format("%02d", this.dayOfMonth)}"

}