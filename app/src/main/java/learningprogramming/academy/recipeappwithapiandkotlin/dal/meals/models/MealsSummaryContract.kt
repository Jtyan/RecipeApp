package learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models

import learningprogramming.academy.recipeappwithapiandkotlin.models.MealSummaryModel
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealsSummaryModel


data class MealSummaryContract(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
) {

    fun toMealModel(): MealSummaryModel {
        return MealSummaryModel(
            idMeal = idMeal,
            strMeal = strMeal,
            strMealThumb = strMealThumb
        )
    }
}

class MealsSummaryContract(val meals: List<MealSummaryContract>) {
    fun toMealsModel(): MealsSummaryModel {
        val mealsByCategory = meals.map {
            it.toMealModel()
        }
        return MealsSummaryModel(meals = mealsByCategory)
    }
}