package com.example.noteapp_jetpack_compose.data.repository

import com.example.noteapp_jetpack_compose.Note
import com.example.noteapp_jetpack_compose.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {


    suspend fun getAll(): List<Note>{
        return noteDao.getAll()
    }

    suspend fun insert(note: Note)
    {
         noteDao.insert(note)
    }

    suspend fun update(note: Note)
    {
        noteDao.update(note)
    }
    suspend fun deleteNote(note: Note)
    {
        noteDao.deleteNote(note)
    }







}