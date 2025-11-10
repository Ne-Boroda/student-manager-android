package com.example.students.data

import androidx.room.*

@Database(
    entities = [StudentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}