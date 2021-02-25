package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
    Scaffold(topBar = {
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
    }) {
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
            .clickable { navigationViewModel.selectDog(dog.id) }) {
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