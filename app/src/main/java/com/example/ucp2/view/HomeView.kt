package com.example.ucp2.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeView(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Welcome to Home",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)


        // Dosen Section
        Button (
            onClick = {
                navController.navigate("home_dosen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Manage Dosen")  // Button text for Dosen section
        }

        // Mata Kuliah Section
        Button(
            onClick = {
                navController.navigate(("home_mk"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Manage Mata Kuliah")  // Button text for Mata Kuliah section
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView(
        navController = rememberNavController()
    )
}