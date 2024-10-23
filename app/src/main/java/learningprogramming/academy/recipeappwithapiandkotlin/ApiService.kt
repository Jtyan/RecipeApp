package learningprogramming.academy.recipeappwithapiandkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealsResponse
}

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiService::class.java)