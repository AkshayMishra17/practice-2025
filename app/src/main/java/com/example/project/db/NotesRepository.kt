package com.example.project.db

import android.provider.ContactsContract

class NotesRepository(private val noteDao: NotesDao) {
    suspend fun getNotes() = noteDao.getAllNotes()
    suspend fun deleteNotes(note : Notes) = noteDao.deleteNote(note)
    suspend fun updateNote(note: Notes) = noteDao.updateNote(note)
    suspend fun insertNote(note: Notes) = noteDao.insertNote(note)

}