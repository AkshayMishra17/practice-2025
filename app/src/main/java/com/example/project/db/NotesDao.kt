package com.example.project.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao{


    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Notes>

    @Insert
    suspend fun insertNote(note: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)




}