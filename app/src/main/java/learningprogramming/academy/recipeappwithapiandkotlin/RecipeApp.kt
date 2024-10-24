package learningprogramming.academy.recipeappwithapiandkotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealCategoriesContract
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealSummaryModel
import learningprogramming.academy.recipeappwithapiandkotlin.screens.MealDetailsUI
import learningprogramming.academy.recipeappwithapiandkotlin.screens.MealsByCategoryUI
import learningprogramming.academy.recipeappwithapiandkotlin.screens.CategoriesUI
import learningprogramming.academy.recipeappwithapiandkotlin.screens.Screen

@Composable
fun RecipeApp(modifier: Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            CategoriesUI(modifier = modifier, navigationToMealsByCategoryUI = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.MealsByCategoryScreen.route)
            })
        }
        composable(route = Screen.MealsByCategoryScreen.route) {
            val mealCategoriesContract = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<MealCategoriesContract>("cat") ?: MealCategoriesContract("", "", "", "")
            MealsByCategoryUI(modifier = modifier, mealCategoriesContract, navigationToMealDetailScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set("meal", it)
                navController.navigate(Screen.MealDetailScreen.route)
            })
        }
        composable(route = Screen.MealDetailScreen.route) {
            val meal = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<MealSummaryModel>("meal") ?: MealSummaryModel()
            MealDetailsUI(modifier = modifier, meal)
        }
    }
}