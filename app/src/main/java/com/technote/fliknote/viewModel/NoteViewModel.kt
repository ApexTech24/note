package com.technote.fliknote.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.technote.fliknote.db.NoteDatabase
import com.technote.fliknote.model.Note
import com.technote.fliknote.repos.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var repository: NoteRepository
    init {
        val dao = NoteDatabase.getDB(application).getNoteDao()
        repository=NoteRepository(dao)
    }

    fun insertNote(note: Note)= repository.insertNote(note)

    fun updateNote(note: Note){
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun getAllNote(): LiveData<List<Note>> =repository.getAllNote()

    fun getNoteById(id:Long): LiveData<Note> =repository.getNoteById(id)
}