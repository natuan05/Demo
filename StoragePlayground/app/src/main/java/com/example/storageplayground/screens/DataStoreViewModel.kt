package com.example.storageplayground.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.storageplayground.data.datastore.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DataStoreViewModel(private val repository: SettingsRepository) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = repository.isDarkMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // Bắt đầu khi UI hiển thị
            initialValue = false
        )

    // Hàm để UI gọi khi muốn thay đổi giá trị
    fun setDarkMode(isDark: Boolean) {
        // Chạy một coroutine mới trong viewModelScope để gọi hàm suspend
        viewModelScope.launch {
            repository.setDarkMode(isDark)
        }
    }
}

class DataStoreViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DataStoreViewModel(SettingsRepository(application)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}