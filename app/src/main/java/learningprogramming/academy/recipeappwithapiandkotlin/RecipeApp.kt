package learningprogramming.academy.recipeappwithapiandkotlin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(modifier: Modifier, navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeUI(modifier = modifier, viewState = viewState, navigationToMealsByCategoryUI = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.MealsByCategoryScreen.route)
            })
        }
        composable(route = Screen.MealsByCategoryScreen.route) {
            val category = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("cat") ?: Category("", "", "", "")
            MealsByCategoryUI(category = category)
        }
    }
}