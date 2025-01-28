package com.example.singup_app.data.db_room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserNotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(note: UserNotes)

//    @Query("SELECT * FROM user_notes ORDER BY id DESC")
//    suspend fun getAllNotes(): List<UserNotes>
}