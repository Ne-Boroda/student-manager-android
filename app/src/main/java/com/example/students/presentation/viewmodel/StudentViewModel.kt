package com.example.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.students.data.StudentRepository
import com.example.students.domain.usecases.GetStudentsUseCase

class StudentViewModel: ViewModel() {
    private val getStudentsUseCase = GetStudentsUseCase(StudentRepository())
    val students = getStudentsUseCase.execute()
}