package com.example.students.domain.usecases

import com.example.students.data.StudentRepository

class RemoveStudentUseCase(private val repository: StudentRepository) {
    fun execute(position: Int) {
        repository.removeStudent(position)
    }
}