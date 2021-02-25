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
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.dogsData
import com.example.androiddevchallenge.ui.DogDetails
import com.example.androiddevchallenge.ui.DogsList
import com.example.androiddevchallenge.ui.NavigationViewModel
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.model.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

lateinit var screenSpecs: ScreenSpecs

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                screenSpecs = calculateScreenSpecs()
                MyApp(navigationViewModel, dogsData)
            }
        }
    }

    override fun onBackPressed() {
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun MyApp(navigationViewModel: NavigationViewModel, dogsData: List<Dog>) {
    Crossfade(navigationViewModel.currentScreenState.value) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.DogsList -> DogsList(navigationViewModel, dogsData)
                is Screen.DogDetails -> DogDetails(navigationViewModel, dogsData)
            }
        }
    }
}

@Composable
fun calculateScreenSpecs(): ScreenSpecs {
    val displayMetrics: DisplayMetrics = LocalContext.current.resources.displayMetrics
    return ScreenSpecs(
        density = displayMetrics.density,
        pxHeight = displayMetrics.heightPixels * 1F,
        pxWidth = displayMetrics.widthPixels * 1F
    )
}

class ScreenSpecs(
    var density: Float = 0F,
    var pxWidth: Float = 0F,
    var pxHeight: Float = 0F
) {

    var dpWidth: Dp = 1.dp * pxWidth / density
    var dpHeight = 1.dp * pxHeight / density
}
