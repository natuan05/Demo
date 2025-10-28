package com.example.storageplayground.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.storageplayground.data.network.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkScreen(navController: NavController) {

    // Khởi tạo ViewModel (ViewModel này không cần Factory)
    val viewModel: NetworkViewModel = viewModel()

    // Lắng nghe các StateFlow
    val post by viewModel.post.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("4. Network Demo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { viewModel.fetchPost(1) }, // Gọi API lấy post số 1
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tải dữ liệu từ API (Post 1)")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Hiển thị kết quả
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator() // Hiển thị vòng xoay
                } else if (error != null) {
                    Text(
                        text = error!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium
                    )
                } else if (post != null) {
                    // Hiển thị tiêu đề bài post
                    DisplayPost(post!!)
                }
            }
        }
    }
}

@Composable
fun DisplayPost(post: Post) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "ID: ${post.id}",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Title: ${post.title}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}