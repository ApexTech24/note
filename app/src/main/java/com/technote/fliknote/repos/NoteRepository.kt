package com.technote.fliknote.repos

import androidx.lifecycle.LiveData
import com.technote.fliknote.dao.NoteDao
import com.technote.fliknote.model.Note

class NoteRepository(private val dao: NoteDao) {
    fun insertNote(note: Note)= dao.insertNote(note)

    fun updateNote(note: Note)=dao.updateNote(note)

    fun deleteNote(note: Note)=dao.deleteNote(note)

    fun getAllNote(): LiveData<List<Note>> =dao.getAllNote()
    fun getNoteById(id:Long): LiveData<Note> =dao.getNoteById(id)
}