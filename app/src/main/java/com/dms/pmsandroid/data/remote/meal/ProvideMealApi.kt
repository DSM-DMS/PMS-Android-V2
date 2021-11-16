package com.dms.pmsandroid.data.remote.meal

import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.dms.pmsandroid.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.feature.meal.model.MealResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideMealApi(api: PotatoChipApi) {

    private val mealApi = api.retrofit.create(MealApi::class.java)

    fun getMeal(accessToken: String, dateTime: String): @NonNull Single<Response<MealResponse>> =
        mealApi.getMeal(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMealPicture(
        accessToken: String,
        dateTime: String
    ): @NonNull Single<Response<MealPictureResponse>> =
        mealApi.getMealPicture(accessToken, dateTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}