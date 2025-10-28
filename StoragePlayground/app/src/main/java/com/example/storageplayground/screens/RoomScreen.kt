package com.example.storageplayground.screens

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.storageplayground.data.room.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(navController: NavController) {

    // Lấy Application context
    val application = LocalContext.current.applicationContext as Application

    // Khởi tạo ViewModel dùng Factory
    val viewModel: RoomViewModel = viewModel(
        factory = RoomViewModelFactory(application)
    )

    // Lắng nghe StateFlow
    val allNotes by viewModel.allNotes.collectAsState()

    // State cho ô nhập liệu
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("2. Room Demo") },
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
            // Khu vực nhập liệu
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Ghi chú mới...") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (text.isNotBlank()) {
                            viewModel.insert(text) // Gọi ViewModel
                            text = "" // Xóa ô nhập liệu
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Thêm")
                }
            }

            // Danh sách ghi chú
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(allNotes) { note ->
                    NoteItem(note = note)
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Text(
        text = note.text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}