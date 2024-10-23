package learningprogramming.academy.recipeappwithapiandkotlin

sealed class Screen(val route:String) {
    object RecipeScreen:Screen("recipeUIScreen")
    object MealsByCategoryScreen:Screen("mealsByCategoryScreen")
    object MealDetailScreen:Screen("mealDetailScreen")
}