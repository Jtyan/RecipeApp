package learningprogramming.academy.recipeappwithapiandkotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String,
    val strInstructions: String?,
):Parcelable

class MealsResponse(val meals: List<Meal>)