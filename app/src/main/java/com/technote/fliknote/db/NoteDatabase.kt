package com.technote.fliknote.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technote.fliknote.dao.NoteDao
import com.technote.fliknote.model.Note

@Database(
    entities = [Note::class],
    version = 1
)

abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao():NoteDao

    companion object{
        private var db:NoteDatabase? = null
        fun getDB(context: Context):NoteDatabase{
            if (db==null){
                db= Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db"
                ).allowMainThreadQueries().build()

                return db!!
            }
            return db!!
        }
    }
}