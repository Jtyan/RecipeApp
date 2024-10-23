package learningprogramming.academy.recipeappwithapiandkotlin


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val _mealsState = mutableStateOf(MealsState())
    val mealsState: State<MealsState> = _mealsState

    init {
        fetchCategories()
    }
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    response.categories,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories: ${e.message}"
                )
            }
        }
    }

    fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = recipeService.getMealsByCategory(category)
                _mealsState.value = _mealsState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )
            } catch (e: Exception) {
                _mealsState.value = _mealsState.value.copy(
                    loading = false,
                    error = "Error fetching Meals: ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    data class MealsState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )
}