package com.example.storageplayground.data.file

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class FileRepository(private val context: Context) {

    // Tên file mà chúng ta sẽ dùng
    private val filename = "notepad.txt"

    // Hàm suspend để ghi text vào file
    suspend fun saveToFile(content: String) {
        // withContext(Dispatchers.IO) chuyển coroutine sang luồng I/O
        // (chuyên dụng cho các tác vụ đọc/ghi file, network)
        withContext(Dispatchers.IO) {
            try {
                // context.filesDir là đường dẫn đến bộ nhớ TRONG (Internal)
                val file = File(context.filesDir, filename)

                // .writeText() là một hàm mở rộng của Kotlin
                // tự động ghi đè nội dung file
                file.writeText(content)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Hàm suspend để đọc text từ file
    suspend fun loadFromFile(): String {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(context.filesDir, filename)

                // Nếu file tồn tại, dùng .readText() (hàm mở rộng của Kotlin)
                // để đọc toàn bộ nội dung file
                if (file.exists()) {
                    file.readText()
                } else {
                    "" // Trả về rỗng nếu file chưa tồnG tại
                }
            } catch (e: IOException) {
                e.printStackTrace()
                "" // Trả về rỗng nếu có lỗi
            }
        }
    }
}