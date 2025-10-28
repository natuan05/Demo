package com.example.storageplayground.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // @GET: Định nghĩa đây là một cuộc gọi GET
    // "posts/1": Đây là phần đuôi (endpoint) của URL
    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") postId: Int // @Path("id") sẽ thay thế {id} trong URL
    ): Post // Retrofit tự động chuyển JSON thành data class Post

}