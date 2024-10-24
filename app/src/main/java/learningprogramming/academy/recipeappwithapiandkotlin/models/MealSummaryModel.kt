package learningprogramming.academy.recipeappwithapiandkotlin.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealSummaryModel(
    val idMeal: String = "",
    val strMeal: String = "",
    val strMealThumb: String = "",
) : Parcelable

data class MealsSummaryModel(val meals: List<MealSummaryModel>)
