package com.example.goodfood.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.goodfood.R
import com.example.goodfood.activities.CategoryMealsActivity
import com.example.goodfood.activities.MainActivity
import com.example.goodfood.activities.MealActivity
import com.example.goodfood.adapter.CategoriesAdapter
import com.example.goodfood.databinding.FragmentCategoriesBinding
import com.example.goodfood.viewModel.HomeViewModel

class CategoriesFragment : Fragment() {

    private lateinit var binding :FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCategories()
        viewModel.getCategories()
        onCategoryClick()
    }

    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer {categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rcvCategory.apply {
            layoutManager = GridLayoutManager(context,3 , GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }
    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, it.strCategory)
            startActivity(intent)
        }
    }

}