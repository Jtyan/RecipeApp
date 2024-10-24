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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import learningprogramming.academy.recipeappwithapiandkotlin.MainViewModel
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealCategoriesContract


@Composable
fun CategoriesUI(modifier: Modifier, navigationToMealsByCategoryUI: (MealCategoriesContract) -> Unit) {
    val recipeViewModel: MainViewModel = viewModel()
    val categoryState by recipeViewModel.categoriesState

    recipeViewModel.fetchCategories()
    Box(modifier = modifier) {
        when {
            categoryState.isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            categoryState.error != null -> {
                Text("Error Occurred!")
            }

            else -> {
                CategoryScreen(categories = categoryState.list,
                    onCategoryClick = { category ->
                        navigationToMealsByCategoryUI(category)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<MealCategoriesContract>, onCategoryClick: (MealCategoriesContract) -> Unit) {
    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Ingredients", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.fillMaxWidth().padding(12.dp))
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(
                    mealCategoriesContract = category,
                    onClick = onCategoryClick
                )
            }
        }
    }
    }


@Composable
fun CategoryItem(mealCategoriesContract: MealCategoriesContract, onClick: (MealCategoriesContract) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick(mealCategoriesContract) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(mealCategoriesContract.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
        )
        Text(
            text = mealCategoriesContract.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}