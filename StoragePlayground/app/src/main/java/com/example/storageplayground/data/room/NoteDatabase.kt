package com.example.storageplayground.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// @Database: Định nghĩa CSDL, bao gồm các 'entities' (bảng)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    // Cung cấp hàm abstract để lấy DAO
    abstract fun noteDao(): NoteDao

    // Companion object để tạo CSDL theo mẫu Singleton
    // (đảm bảo chỉ có 1 instance của CSDL trong toàn app)
    companion object {
        // @Volatile: Đảm bảo giá trị của INSTANCE luôn là mới nhất
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // Chỉ tạo CSDL nếu nó chưa tồn tại
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database" // Tên file CSDL trên thiết bị
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}