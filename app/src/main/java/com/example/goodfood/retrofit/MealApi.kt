package com.example.goodfood.retrofit

import com.example.goodfood.pojo.CategoryList
import com.example.goodfood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    //https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("lookup.php")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

//    https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<CategoryList>
}
