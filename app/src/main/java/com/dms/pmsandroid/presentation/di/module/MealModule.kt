package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.data.meal.remote.ProvideMealApi
import com.dms.pmsandroid.domain.meal.repository.MealRepository
import com.dms.pmsandroid.data.meal.repository.MealRepositoryImpl
import com.dms.pmsandroid.presentation.feature.meal.viewmodel.MealViewModel
import org.koin.dsl.module

val mealModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
    single { ProvideMealApi(get(), get()) }
    single { MealViewModel(get()) }
}