package learningprogramming.academy.recipeappwithapiandkotlin


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealCategoriesContract
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.recipeService
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealDetailsModel
import learningprogramming.academy.recipeappwithapiandkotlin.models.MealSummaryModel

class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val _mealsByCategoryState = mutableStateOf(MealsByCategoryState())
    val mealsByCategoryState: State<MealsByCategoryState> = _mealsByCategoryState

    private val _mealDetailsState = mutableStateOf(MealDetailsState())
    val mealDetailsState: State<MealDetailsState> = _mealDetailsState

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    response.categories,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    error = "Error fetching Categories: ${e.message}"
                )
            }
        }
    }

    fun fetchMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val categoryMeals = recipeService.getMealsByCategory(categoryName).toMealsModel()
                _mealsByCategoryState.value = _mealsByCategoryState.value.copy(
                    isLoading = false,
                    list = categoryMeals.meals,
                    error = null
                )
            } catch (e: Exception) {
                Log.e("ViewModel", e.message.toString())
                _mealsByCategoryState.value = _mealsByCategoryState.value.copy(
                    isLoading = false,
                    error = "Error fetching Meals: ${e.message}"
                )
            }
        }
    }

    fun fetchMealDetails(id: String) {
        viewModelScope.launch {
            try {
                val mealDetails = recipeService.getMealDetails(id).toMealDetailsModel()
                _mealDetailsState.value = _mealDetailsState.value.copy(
                    isLoading = false,
                    mealDetails = mealDetails,
                    error = null
                )
            } catch (e: Exception) {
                _mealDetailsState.value = _mealDetailsState.value.copy(
                    isLoading = false,
                    error = "Error fetching Meals: ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val isLoading: Boolean = true,
        val list: List<MealCategoriesContract> = emptyList(),
        val error: String? = null
    )

    data class MealsByCategoryState(
        val isLoading: Boolean = true,
        val list: List<MealSummaryModel> = emptyList(),
        val error: String? = null
    )

    data class MealDetailsState(
        val isLoading: Boolean = true,
        val mealDetails: MealDetailsModel = MealDetailsModel(),
        val error: String? = null
    )

}