package com.example.androiddevchallenge.ui.model

import androidx.annotation.DrawableRes

data class Dog(
    val id: Int,
    val title: String,
    val description: String,
    val specs: DogSpecs,
    @DrawableRes val images: List<Int>,
)

data class DogSpecs(
    val name: String,
    val age: String,
    val breed: String,
    val status: String,
    val vaccination: String,
    val feeding: String,
    val energy: String,
    val reproduction: String,
    val Gender: String
)
