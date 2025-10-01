package com.example.project.vm.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.db.NotesRepository

class NotesVMFactory(private val repo: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesVM(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
