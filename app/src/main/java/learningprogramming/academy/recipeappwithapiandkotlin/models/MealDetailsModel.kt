package learningprogramming.academy.recipeappwithapiandkotlin.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealDetailsModel(
    val idMeal: String = "",
    val strMeal: String = "",
    val strMealThumb: String = "",
    val strInstructions: String = "",
    val strYoutube: String = "",
    val strArea: String = "",
    val strCategory: String = ""
): Parcelable