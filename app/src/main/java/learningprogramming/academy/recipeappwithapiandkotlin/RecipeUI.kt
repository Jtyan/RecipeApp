package learningprogramming.academy.recipeappwithapiandkotlin

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


@Composable
fun RecipeUI(navigationToMealsByCategoryUI: (String) -> Unit) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text("Error Occurred!")
            }

            else -> {
                CategoryScreen(categories = viewState.list,
                    onCategoryClick = { category ->
                        navigationToMealsByCategoryUI(category)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, onCategoryClick: (String) -> Unit) {
    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Ingredients", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.fillMaxWidth().padding(12.dp))
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = onCategoryClick
                )
            }
        }
    }
    }


@Composable
fun CategoryItem(category: Category, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick(category.strCategory) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}