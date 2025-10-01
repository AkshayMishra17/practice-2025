package com.example.project.vm.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.db.Notes
import com.example.project.db.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NotesVM(private val notesRepository: NotesRepository) : ViewModel() {
    private val _notes = MutableStateFlow<List<Notes>>(emptyList())
    var notes: MutableStateFlow<List<Notes>> = _notes

    fun loadNotes(){
        viewModelScope.launch {
            _notes.value = notesRepository.getNotes()
        }
    }


    suspend fun addNotes(note: Notes) {
        notesRepository.insertNote(note)
        loadNotes()
    }

    suspend fun deleteNote(note: Notes) {
        notesRepository.deleteNotes(note)
        loadNotes()
    }

    suspend fun updateNote(note: Notes) {
        notesRepository.updateNote(note)
        loadNotes()
    }

    suspend fun insertNote(note: Notes) {
        notesRepository.insertNote(note)
        loadNotes()
    }
}