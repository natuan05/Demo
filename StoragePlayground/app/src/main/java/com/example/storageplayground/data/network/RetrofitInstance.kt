package com.example.storageplayground.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    // URL gốc của API
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Khởi tạo Moshi (để chuyển đổi JSON <-> Kotlin)
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Khởi tạo Retrofit (dùng lazy để chỉ tạo khi cần)
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Thêm bộ chuyển đổi Moshi
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    // Cung cấp một thực thể của ApiService
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}