package learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models

import learningprogramming.academy.recipeappwithapiandkotlin.models.MealDetailsModel


data class MealDetailsContract(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strDrinkAlternate: String,
    val strInstructions: String,
    val strYoutube: String,
    val strCategory: String,
    val strArea: String
)

class MealsDetailsContract(val meals: List<MealDetailsContract>) {

    fun toMealDetailsModel(): MealDetailsModel {
        val meal = meals.first()
        return MealDetailsModel(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strMealThumb = meal.strMealThumb,
            strInstructions = meal.strInstructions,
            strYoutube = meal.strYoutube,
            strCategory = meal.strCategory,
            strArea = meal.strArea
        )
    }
}