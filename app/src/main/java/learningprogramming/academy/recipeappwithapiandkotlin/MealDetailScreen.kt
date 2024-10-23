package learningprogramming.academy.recipeappwithapiandkotlin

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MealDetailScreen(modifier: Modifier, meal: Meal) {
    Log.d("MealDetailScreen", meal.strMeal)
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(meal.strMeal, textAlign = TextAlign.Center)
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = "${meal.strMeal} Thumbnail",
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(.50f)
        )
        Text(
            text = if (!meal.strInstructions.isNullOrBlank()) meal.strInstructions else "no Instruction",
            textAlign = TextAlign.Justify,
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
}