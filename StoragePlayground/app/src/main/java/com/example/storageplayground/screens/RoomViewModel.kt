package com.example.storageplayground.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.storageplayground.data.room.Note
import com.example.storageplayground.data.room.NoteDatabase
import com.example.storageplayground.data.room.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) : ViewModel() {

    // Khởi tạo Repository (đây là cách làm đơn giản,
    // cách làm chuẩn sẽ dùng Hilt/Dagger để inject)
    private val repository: NoteRepository

    // Lấy StateFlow danh sách ghi chú
    val allNotes: StateFlow<List<Note>>

    init {
        // Lấy DAO từ Database
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        // Khởi tạo Repository
        repository = NoteRepository(noteDao)

        // Chuyển Flow thành StateFlow
        allNotes = repository.allNotes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // Ban đầu danh sách trống
        )
    }

    // Hàm để UI gọi khi thêm ghi chú
    fun insert(noteText: String) = viewModelScope.launch {
        repository.insert(Note(text = noteText))
    }
}

// Factory cho RoomViewModel (vì nó cần Application)
class RoomViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}