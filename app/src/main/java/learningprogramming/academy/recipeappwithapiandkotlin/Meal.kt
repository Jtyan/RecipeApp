package learningprogramming.academy.recipeappwithapiandkotlin

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)

data class MealsResponse(val meals: List<Meal>)