package com.example.storageplayground.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity báo cho Room biết đây là một bảng
@Entity(tableName = "notes_table")
data class Note(
    // @PrimaryKey báo cho Room biết đây là khóa chính
    // autoGenerate = true: Tự động tăng ID (1, 2, 3...)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Giá trị mặc định là 0 cho ID tự tăng

    val text: String
)