package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.meal.remote.ProvideMealApi
import com.dms.pmsandroid.data.meal.repository.MealRepository
import com.dms.pmsandroid.data.meal.repository.MealRepositoryImpl
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.dsl.module

val mealModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
    single { ProvideMealApi(get(), get()) }
    single { MealViewModel(get()) }
}