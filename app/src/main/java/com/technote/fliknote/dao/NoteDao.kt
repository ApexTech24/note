package com.technote.fliknote.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.technote.fliknote.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("Select * from tbl_note")
    fun getAllNote(): LiveData<List<Note>>

    @Query("Select * from tbl_note where id = :id")
    fun getNoteById(id:Long): LiveData<Note>
}