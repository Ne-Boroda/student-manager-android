package com.example.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.students.data.Student
import com.example.students.data.StudentRepository
import com.example.students.domain.usecases.GetStudentsUseCase
import com.example.students.domain.usecases.AddStudentUseCase
import com.example.students.domain.usecases.RemoveStudentUseCase
import com.example.students.domain.usecases.RestoreStudentUseCase

class StudentViewModel: ViewModel() {
    val repository = StudentRepository()

    private val getStudentsUseCase = GetStudentsUseCase(repository)
    private val addStudentsUseCase = AddStudentUseCase(repository)
    private val removeStudentUseCase = RemoveStudentUseCase(repository)
    private val restoreStudentUseCase = RestoreStudentUseCase(repository)

    val students: List<Student>
        get() = repository.students

    fun addStudent(student: Student)
    {
        addStudentsUseCase.execute(student)
    }

    private var recentlyDeletedStudent: Student? = null
    private var recentlyDeletedPosition: Int = -1

    val deletedPosition: Int
        get() = recentlyDeletedPosition

    fun deleteStudent(position: Int)
    {
        recentlyDeletedStudent = students[position]
        recentlyDeletedPosition = position
        removeStudentUseCase.execute(position)
    }

    fun restoreStudent() {
        recentlyDeletedStudent?.let { student ->
            if (recentlyDeletedPosition in 0..students.size) {
                restoreStudentUseCase.execute(student, recentlyDeletedPosition)
                recentlyDeletedStudent = null
                recentlyDeletedPosition = -1
            }
        }
    }
}