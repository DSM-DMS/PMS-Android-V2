package com.dms.pmsandroid.presentation.feature.meal.entity

data class NeedUpdateMealItems(val startPosition: Int, val meals: HashMap<Int, MealItem>)