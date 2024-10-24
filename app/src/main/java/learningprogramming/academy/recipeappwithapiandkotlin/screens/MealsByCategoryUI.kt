package learningprogramming.academy.recipeappwithapiandkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import learningprogramming.academy.recipeappwithapiandkotlin.MainViewModel
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealCategoriesContract
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealSummaryModel

@Composable
fun MealsByCategoryUI(
    modifier: Modifier,
    mealCategoriesContract: MealCategoriesContract,
    navigationToMealDetailScreen: (MealSummaryModel) -> Unit
) {
    val recipeViewModel: MainViewModel = viewModel()
    val mealsByCategoryState by recipeViewModel.mealsByCategoryState


    recipeViewModel.fetchMealsByCategory(mealCategoriesContract.strCategory)

    Box(modifier = modifier.fillMaxSize()) {
        when {
            mealsByCategoryState.isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            mealsByCategoryState.error != null -> {
                Text("Error Occurred!")
            }

            else -> {
                MealCategoryScreen(mealCategoriesContract, meals = mealsByCategoryState.list,
                    onMealClick = { meal ->
                        navigationToMealDetailScreen(meal)
                    })
            }
        }
    }
}

@Composable
fun MealCategoryScreen(
    mealCategoriesContract: MealCategoriesContract,
    meals: List<MealSummaryModel>,
    onMealClick: (MealSummaryModel) -> Unit
) {
    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(mealCategoriesContract.strCategory, style = MaterialTheme.typography.headlineLarge)
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(meals, key = { it.idMeal }) { meal ->

                MealsByCategoryItem(meal = meal, onMealClick = onMealClick)
            }
        }
    }
}

@Composable
fun MealsByCategoryItem(meal: MealSummaryModel, onMealClick: (MealSummaryModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onMealClick(meal) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clip(CircleShape)
        )
        Text(
            text = meal.strMeal,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
