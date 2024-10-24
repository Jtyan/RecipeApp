package learningprogramming.academy.recipeappwithapiandkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import learningprogramming.academy.recipeappwithapiandkotlin.MainViewModel
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealSummaryModel

@Composable
fun MealDetailsUI(modifier: Modifier, meal: MealSummaryModel) {
    val recipeViewModel: MainViewModel = viewModel()
    val mealDetailsState by recipeViewModel.mealDetailsState
    recipeViewModel.fetchMealDetails(meal.idMeal)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            meal.strMeal,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = "${mealDetailsState.mealDetails.strMealThumb} Thumbnail",
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Text(
            text =
                if (!mealDetailsState.mealDetails.strInstructions.isNullOrBlank()) {
                    mealDetailsState.mealDetails.strInstructions
                } else {
                    "no Instruction"
                },
            textAlign = TextAlign.Justify,
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
}