package com.technote.fliknote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val title :String,
    val description :String,
    var date: String,
    var time: String
)
