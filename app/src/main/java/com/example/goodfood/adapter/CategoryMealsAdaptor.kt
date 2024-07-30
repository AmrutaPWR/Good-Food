package com.example.goodfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goodfood.databinding.CategoryItemBinding
import com.example.goodfood.databinding.MealItemBinding
import com.example.goodfood.pojo.MealList
import com.example.goodfood.pojo.MealsByCategory
import com.example.goodfood.pojo.MealsByCategoryList

class CategoryMealsAdaptor : RecyclerView.Adapter<CategoryMealsAdaptor.CategoryMealsViewModel>() {

    private var mealsList = ArrayList<MealsByCategory>()

    fun setMealsList(mealList: List<MealsByCategory>){
        this.mealsList = mealList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    inner class CategoryMealsViewModel(val binding: MealItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
        return CategoryMealsViewModel(MealItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
       return mealsList.size
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = mealsList[position].strMeal
    }
}