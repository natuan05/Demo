package com.example.storageplayground.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.storageplayground.data.file.FileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FileViewModel(application: Application) : ViewModel() {

    private val repository = FileRepository(application)

    // Dùng MutableStateFlow để giữ nội dung của file text
    private val _fileContent = MutableStateFlow("")
    val fileContent: StateFlow<String> = _fileContent.asStateFlow()

    init {
        // Khi ViewModel khởi tạo, tự động tải nội dung từ file
        loadContent()
    }

    fun loadContent() {
        viewModelScope.launch {
            _fileContent.value = repository.loadFromFile()
        }
    }

    fun saveContent(content: String) {
        viewModelScope.launch {
            repository.saveToFile(content)
            // Sau khi lưu, cập nhật StateFlow để UI đồng bộ
            _fileContent.value = content
        }
    }
}

// Factory cho FileViewModel
class FileViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FileViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}