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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.model.Dog

@Composable
fun DogsList(navigationViewModel: NavigationViewModel, dogsData: List<Dog>) {
    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(R.drawable.app_logo),
                        contentDescription = null
                    )
                    Text(text = "Puppy adoption app", style = MaterialTheme.typography.h5)
                    Spacer(Modifier.width(40.dp))
                }
            }
        }
    ) {
        LazyColumn {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            for (i in dogsData.indices step 2) {
                val firstDog = dogsData[i]
                val secondDog = dogsData[i + 1]
                item {
                    Row(modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp)) {
                        DogCard(navigationViewModel, firstDog)
                        DogCard(navigationViewModel, secondDog)
                    }
                }
            }
        }
    }
}


@Composable
fun DogCard(navigationViewModel: NavigationViewModel, dog: Dog) {
    Card(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .fillMaxWidth(if (dog.id % 2 == 0) 0.5f else 1f)
            .clickable { navigationViewModel.selectDog(dog.id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(dog.images[0]),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Surface(
                modifier = Modifier
                    .height(50.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    text = dog.specs.name + ": " + dog.title,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
