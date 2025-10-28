package com.example.storageplayground.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storageplayground.data.network.NetworkRepository
import com.example.storageplayground.data.network.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworkViewModel : ViewModel() {

    private val repository = NetworkRepository()

    // StateFlow để giữ bài post
    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post.asStateFlow()

    // StateFlow để báo hiệu đang tải
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // StateFlow cho thông báo lỗi
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Hàm để UI gọi
    fun fetchPost(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _post.value = null

            val result = repository.getPost(id)
            if (result != null) {
                _post.value = result
            } else {
                _error.value = "Không thể tải dữ liệu"
            }
            _isLoading.value = false
        }
    }
}