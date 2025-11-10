package com.example.students.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students ORDER BY NAME ASC")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Insert
    suspend fun insertStudent(student: StudentEntity)

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Long)
}