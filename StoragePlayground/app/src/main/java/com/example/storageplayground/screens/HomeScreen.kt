package com.example.storageplayground.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storageplayground.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Android Storage Demos") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DemoButton(
                text = "1. DataStore (Preferences)",
                onClick = { navController.navigate(Routes.DATASTORE) }
            )
            DemoButton(
                text = "2. Room Database",
                onClick = { navController.navigate(Routes.ROOM) }
            )
            DemoButton(
                text = "3. File Storage (Internal)",
                onClick = { navController.navigate(Routes.FILE) }
            )
            DemoButton(
                text = "4. Network (Retrofit)",
                onClick = { navController.navigate(Routes.NETWORK) }
            )
            DemoButton(
                text = "5. Firebase (Cloud)",
                onClick = { navController.navigate(Routes.FIREBASE) }
            )
        }
    }
}

@Composable
private fun DemoButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(280.dp)
    ) {
        Text(text)
    }
    Spacer(modifier = Modifier.height(16.dp))
}