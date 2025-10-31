package com.example.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.students.data.Student
import com.example.students.data.StudentRepository
import com.example.students.domain.usecases.GetStudentsUseCase
import com.example.students.domain.usecases.AddStudentUseCase
import com.example.students.domain.usecases.RemoveStudentUseCase

class StudentViewModel: ViewModel() {
    val repository = StudentRepository()

    private val getStudentsUseCase = GetStudentsUseCase(repository)
    private val addStudentsUseCase = AddStudentUseCase(repository)
    private val removeStudentUseCase = RemoveStudentUseCase(repository)

    val students = getStudentsUseCase.execute()

    fun addStudent(student: Student)
    {
        addStudentsUseCase.execute(student)
    }

    fun removeStudent(position: Int)
    {
        removeStudentUseCase.execute(position)
    }
}