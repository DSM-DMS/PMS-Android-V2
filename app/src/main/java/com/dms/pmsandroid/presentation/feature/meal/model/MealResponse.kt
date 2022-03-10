package com.dms.pmsandroid.presentation.feature.meal.model

import com.dms.pmsandroid.domain.meal.entity.Meal
import java.lang.Exception

data class MealResponse(val breakfast: Any?, val lunch: Any?, val dinner: Any?)

fun MealResponse.toEntity() =
    Meal(
        breakfast = try {
            breakfast as ArrayList<String>
        } catch (e: Exception) {
            ArrayList()
        },
        lunch = try {
            lunch as ArrayList<String>
        } catch (e: Exception) {
            ArrayList()
        },
        dinner = try {
            dinner as ArrayList<String>
        } catch (e: Exception) {
            ArrayList()
        }
    )
