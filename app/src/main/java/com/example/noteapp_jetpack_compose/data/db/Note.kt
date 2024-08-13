package com.example.noteapp_jetpack_compose

import android.graphics.Color
import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title :String,
    val description :String,
    val isFavorite :Boolean,
    val color: Long,
    val createdTime:String?,
    val createdDate:String?
)
