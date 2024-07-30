package com.example.goodfood.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.goodfood.activities.MainActivity
import com.example.goodfood.activities.MealActivity
import com.example.goodfood.adapter.FavouritesMealAdapter
import com.example.goodfood.databinding.FragmentFavBinding
import com.example.goodfood.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favouritesMealAdapter: FavouritesMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        observeFavourites()
        onFavouritesClick()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favouritesMealAdapter.differ.currentList[position])

//                Snackbar.make(requireView(), "Meal Deleted", Snackbar.LENGTH_LONG).setAction(
//                    "undo",
//                    View.OnClickListener {
//                        viewModel.insertMeal(favouritesMealAdapter.differ.currentList[position])
//                    }
//                ).show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rcvFavourites)

    }


    private fun onFavouritesClick() {
        favouritesMealAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID, meal.idMeal)
            intent.putExtra(HomeFragment.MEAL_NAME, meal.strMeal)
            intent.putExtra(HomeFragment.MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        favouritesMealAdapter = FavouritesMealAdapter()
        binding.rcvFavourites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favouritesMealAdapter
        }
    }

    private fun observeFavourites() {
        viewModel.observeFavouritesMealsLiveData().observe(requireActivity(), Observer { meals ->
            favouritesMealAdapter.differ.submitList(meals)
        })
    }

}