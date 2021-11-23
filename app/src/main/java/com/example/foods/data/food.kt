package com.example.foods.data

import com.example.foods.R
import kotlin.random.Random

data class Food(val name: String, val img: Int)

val listFood = listOf<Food>(
    Food("Tra Xanh", R.drawable.traxanh),
    Food("Tra Sua", R.drawable.trasua),
    Food("My Cay", R.drawable.mycay),
    Food("My Quang", R.drawable.myquang),
    Food("My Tom", R.drawable.mytom)
)

var food: Food = listFood[Random.nextInt(0, listFood.size - 1)]


