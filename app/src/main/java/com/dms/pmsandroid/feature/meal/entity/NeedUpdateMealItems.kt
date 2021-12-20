package com.dms.pmsandroid.feature.meal.entity

data class NeedUpdateMealItems(val startPosition: Int, val meals: HashMap<Int, MealItem>)