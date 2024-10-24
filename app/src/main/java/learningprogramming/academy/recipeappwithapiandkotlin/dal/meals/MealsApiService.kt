package learningprogramming.academy.recipeappwithapiandkotlin.dal.meals

import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealCategoriesResponse
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealsDetailsContract
import learningprogramming.academy.recipeappwithapiandkotlin.dal.meals.models.MealsSummaryContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("categories.php")
    suspend fun getCategories(): MealCategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") categoryName: String
    ): MealsSummaryContract

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") id: String
    ): MealsDetailsContract
}

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(MealsApiService::class.java)