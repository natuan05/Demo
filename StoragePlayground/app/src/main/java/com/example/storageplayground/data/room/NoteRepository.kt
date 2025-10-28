package com.example.storageplayground.data.room

import kotlinx.coroutines.flow.Flow

// Repository nhận vào DAO
class NoteRepository(private val noteDao: NoteDao) {

    // Lấy Flow danh sách ghi chú từ DAO
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    // Hàm suspend để chèn ghi chú
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
}