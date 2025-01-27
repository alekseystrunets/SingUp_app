
package com.example.singup_app.data.db_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_notes")
data class UserNotes(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
   @ColumnInfo("header") val header: String,
    @ColumnInfo("message")val message: String,
    val date: String
)
