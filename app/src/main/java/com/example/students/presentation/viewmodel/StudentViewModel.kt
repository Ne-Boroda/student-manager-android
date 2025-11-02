package com.example.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.students.data.Student
import com.example.students.data.StudentRepository
import com.example.students.domain.usecases.GetStudentsUseCase
import com.example.students.domain.usecases.AddStudentUseCase
import com.example.students.domain.usecases.RemoveStudentUseCase
import com.example.students.domain.usecases.RestoreStudentUseCase
import com.example.students.domain.usecases.UpdateStudentUseCase

class StudentViewModel: ViewModel() {
    val repository = StudentRepository()

    private val addStudentsUseCase = AddStudentUseCase(repository)
    private val updateStudentUseCase = UpdateStudentUseCase(repository)
    private val removeStudentUseCase = RemoveStudentUseCase(repository)
    private val restoreStudentUseCase = RestoreStudentUseCase(repository)

    val students: List<Student>
        get() = repository.students

    fun addStudent(student: Student)
    {
        addStudentsUseCase.execute(student)
    }

    fun updateStudent(position: Int, student: Student) {
        updateStudentUseCase.execute(position, student)
    }

    private var recentlyDeletedStudent: Student? = null
    private var recentlyDeletedPosition: Int = -1

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