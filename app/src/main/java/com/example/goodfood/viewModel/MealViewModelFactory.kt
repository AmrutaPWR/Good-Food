package com.example.goodfood.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.DB.MealDatabase

class MealViewModelFactory(private val mealDatabase: MealDatabase):ViewModelProvider.Factory {
    override fun <T:ViewModel> create (modelClass: Class<T>):T{
        return MealViewModel(mealDatabase) as T
    }
}