package learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealCategoriesContract(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
): Parcelable

data class MealCategoriesResponse(val categories: List<MealCategoriesContract>)