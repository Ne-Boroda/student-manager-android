package com.example.students.data

import android.content.Context
import com.example.students.data.DatabaseProvider
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val context: Context) {
    private val database = DatabaseProvider.getDatabase(context)
    private val studentDao = database.studentDao()

    val students: Flow<List<StudentEntity>>
        get() = studentDao.getAllStudents()

    suspend fun addStudent(student: StudentEntity) {
        studentDao.insertStudent(student)
    }

    suspend fun updateStudent(student: StudentEntity) {
        studentDao.updateStudent(student)
    }

    suspend fun removeStudent(student: StudentEntity) {
        studentDao.deleteStudent(student)
    }
}