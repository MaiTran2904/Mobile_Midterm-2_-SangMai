package com.example.foods.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foods.data.food
import kotlinx.coroutines.launch
import com.example.foods.data.listFood
import kotlin.random.Random


@Composable
fun HomePage() {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    // Alert Dialog
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {
                IconButton(
                    onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "....")
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Random") },
                onClick = {
                    food
                    showDialog.value = true
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        content = {
            if (!showDialog.value) {
                if (listFood != null) {
                    Column() {
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(listFood) { index ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = index.img),
                                        contentDescription = "anh",
                                        modifier = Modifier
                                            .height(80.dp)
                                            .width(100.dp)
                                    )
                                    Text(
                                        text = index.name,
                                        modifier = Modifier.padding(20.dp),
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                Divider()
                            }
                        }
                    }
                } else {
                    Text("Error")
                }
            } else {
                AlertDialog(
                    onDismissRequest = { showDialog.value = true },
                    title = { },
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = food.img),
                                contentDescription = "anh",
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(100.dp)
                            )
                            Text(
                                text = food.name,
                                modifier = Modifier.padding(20.dp),
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }

//                        Text(
//                            food.name,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.fillMaxWidth()
//                        )
                    },

                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog.value = false

                            },
                            modifier = Modifier.background(
                                Color(0xFFFFD300)
                            )
                        ) {
                            Text(text = "Yes")
                        }
                    },

                    dismissButton = {
                        Button(
                            onClick = {
                                showDialog.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    )

}

