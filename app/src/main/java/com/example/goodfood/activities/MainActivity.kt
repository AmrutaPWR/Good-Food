package com.example.goodfood.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.goodfood.DB.MealDatabase
import com.example.goodfood.R
import com.example.goodfood.viewModel.HomeViewModel
import com.example.goodfood.viewModel.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
     val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
         val homeViewModelFactory = HomeViewModelFactory(mealDatabase)
         ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = Navigation.findNavController(this, R.id.host_fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}