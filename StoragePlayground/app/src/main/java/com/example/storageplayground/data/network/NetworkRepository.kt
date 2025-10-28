package com.example.storageplayground.data.network

// Repository này đơn giản là gọi API
// Trong một app thật, nó có thể lưu kết quả vào Room
class NetworkRepository {

    private val apiService = RetrofitInstance.api

    suspend fun getPost(id: Int): Post? {
        return try {
            apiService.getPostById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            null // Trả về null nếu có lỗi
        }
    }
}