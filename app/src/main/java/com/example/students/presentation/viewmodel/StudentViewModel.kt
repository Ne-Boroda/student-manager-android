package com.example.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.students.data.StudentEntity
import com.example.students.data.StudentRepository
import com.example.students.domain.usecases.GetStudentsUseCase
import com.example.students.domain.usecases.AddStudentUseCase
import com.example.students.domain.usecases.RemoveStudentUseCase
import com.example.students.domain.usecases.UpdateStudentUseCase
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository): ViewModel() {

    private val getStudentsUseCase = GetStudentsUseCase(repository)
    private val addStudentsUseCase = AddStudentUseCase(repository)
    private val updateStudentUseCase = UpdateStudentUseCase(repository)
    private val removeStudentUseCase = RemoveStudentUseCase(repository)

    val students = getStudentsUseCase.execute()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addStudent(name: String, age: Int, averageGrade: Double)
    {
        viewModelScope.launch {
            val newStudent = StudentEntity(
                name = name,
                age = age,
                averageGrade = averageGrade
            )
            addStudentsUseCase.execute(newStudent)
        }
    }

    fun updateStudent(student: StudentEntity) {
        viewModelScope.launch {
            updateStudentUseCase.execute(student)
        }
    }

    fun removeStudent(student: StudentEntity)
    {
        viewModelScope.launch {
            removeStudentUseCase.execute(student)
        }
    }
}