package com.example.students.domain.usecases

import com.example.students.data.StudentEntity
import com.example.students.data.StudentRepository

class RemoveStudentUseCase(private val repository: StudentRepository) {
    suspend fun execute(student : StudentEntity) {
        repository.removeStudent(student)
    }
}