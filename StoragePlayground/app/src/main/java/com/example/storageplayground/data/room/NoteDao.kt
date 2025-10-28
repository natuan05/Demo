package com.example.storageplayground.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao // Báo cho Room biết đây là một Data Access Object
interface NoteDao {

    // @Insert: Hàm để chèn
    // onConflict: Nếu có xung đột (ví dụ: trùng ID), thì bỏ qua
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note) // Dùng 'suspend' vì đây là thao tác I/O

    // @Query: Dùng để viết câu lệnh SQL
    // Lấy tất cả các ghi chú từ bảng, sắp xếp theo ID giảm dần
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>> // Dùng Flow để tự động cập nhật UI
}