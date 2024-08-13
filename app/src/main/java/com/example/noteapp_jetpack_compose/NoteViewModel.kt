package com.example.noteapp_jetpack_compose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp_jetpack_compose.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
//    val noteDao = NoteDatabase.getDatabase(application).noteDao()

    private val _allNotes = MutableStateFlow<List<Note>?>(null)
    val allNotes : StateFlow<List<Note>?> get() = _allNotes



    init {
        getAllNotes()
    }



    fun getAllNotes() {
        viewModelScope.launch {
            _allNotes.value = noteRepository.getAll()
            println("All notes: ${_allNotes.value}")
        }
    }
    fun insertNote(note:Note){
        viewModelScope.launch {
            noteRepository.insert(note)
            getAllNotes()
        }
    }

    fun updateNote(note:Note){
        viewModelScope.launch {
            noteRepository.update(note)
            getAllNotes()

        }
    }



    fun deleteNote(note:Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            getAllNotes()
        }
    }


}