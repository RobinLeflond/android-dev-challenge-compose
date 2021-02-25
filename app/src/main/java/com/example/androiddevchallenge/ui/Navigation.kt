/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
