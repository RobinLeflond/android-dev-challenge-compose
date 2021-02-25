package com.example.androiddevchallenge.ui

import androidx.annotation.MainThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.Screen.DogsList


sealed class Screen {
    object DogsList : Screen()
    object DogDetails : Screen()
}

class NavigationViewModel : ViewModel() {

    var currentScreenState: MutableState<Screen> = mutableStateOf(DogsList)
    var selectedDogState: MutableState<Int> = mutableStateOf(0)

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreenState.value != DogsList
        currentScreenState.value = DogsList
        return wasHandled
    }

    @MainThread
    fun selectDog(dogId: Int) {
        selectedDogState.value = dogId
        navigateTo(Screen.DogDetails)
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreenState.value = screen
    }
}
