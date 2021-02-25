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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.screenSpecs
import com.example.androiddevchallenge.ui.model.Dog
import com.example.androiddevchallenge.ui.model.DogSpecs

@Composable
fun DogDetails(navigationViewModel: NavigationViewModel, dogsData: List<Dog>) {
    val dog = dogsData[navigationViewModel.selectedDogState.value]
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}, backgroundColor = Color.Black) {
                Text(modifier = Modifier.padding(10.dp), text = "Adopt Me!", color = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item { DogImages(dog.images) }
                item { DogTitle(dog.specs.name + ": " + dog.title) }
                item { DogDescription(dog.description) }
                item { DogSpecs(dog.specs) }
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        )
    }
}

val selectedTab = mutableStateOf(0)

@Composable
fun DogImages(images: List<Int>) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.5.dp, bottom = 2.5.dp),
        indicator = {},
        divider = {}
    ) {
        for (i in images.indices)
            Image(
                painter = painterResource(images[i]),
                contentDescription = null, // decorative
                modifier = Modifier
                    .size(screenSpecs.dpWidth - 80.dp)
                    .padding(2.5.dp)
                    .clickable { selectedTab.value = i }
            )
    }
}

@Composable
fun DogTitle(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        text = title,
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun DogDescription(description: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        text = description,
        style = MaterialTheme.typography.subtitle1,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun DogSpecs(specs: DogSpecs) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Name: " + specs.name,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Gender: " + specs.Gender,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Age: " + specs.age,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Reproduction: " + specs.reproduction,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Status: " + specs.status,
                style = MaterialTheme.typography.subtitle2
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Breed: " + specs.breed,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Energy: " + specs.energy,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Feeding: " + specs.feeding,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Vaccination: " + specs.vaccination,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}