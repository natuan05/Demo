package com.example.storageplayground.data.network

import com.squareup.moshi.Json

// Dùng data class của Kotlin để khớp với cấu trúc JSON
data class Post(
    @field:Json(name = "id") // Đảm bảo tên biến khớp với key trong JSON
    val id: Int,

    @field:Json(name = "title")
    val title: String

    // Chúng ta có thể bỏ qua các trường khác như "body", "userId"
    // vì Moshi sẽ tự động bỏ qua chúng
)