package com.example.storageplayground.screens

import android.app.Application
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileScreen(navController: NavController) {

    val application = LocalContext.current.applicationContext as Application

    val viewModel: FileViewModel = viewModel(
        factory = FileViewModelFactory(application)
    )

    // Lấy nội dung file từ StateFlow
    val fileContent by viewModel.fileContent.collectAsState()

    // Tạo một state nội bộ cho ô text,
    // chỉ cập nhật khi nội dung file tải xong
    var text by remember { mutableStateOf("") }

    // Dùng LaunchedEffect để đồng bộ fileContent (từ file)
    // vào 'text' (ô nhập liệu) CHỈ MỘT LẦN khi fileContent thay đổi
    LaunchedEffect(fileContent) {
        if (text.isEmpty()) { // Chỉ cập nhật nếu ô text đang trống
            text = fileContent
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("3. File Storage Demo") },
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
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Nhập nội dung...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Cho ô text chiếm hết không gian
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.saveContent(text)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lưu vào File")
            }
        }
    }
}