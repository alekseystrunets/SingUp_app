package com.example.singup_app.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

import com.example.singup_app.data.db_room.UserNotes
import com.example.singup_app.data.db_room.UserNotesDao

@Database(entities = [UserNotes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserNotesDao
    abstract fun userNotesDao(): UserNotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}