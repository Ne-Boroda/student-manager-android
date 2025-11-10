package com.example.students.domain.usecases

import com.example.students.data.StudentRepository
import com.example.students.data.StudentEntity

class AddStudentUseCase(private val repository: StudentRepository) {
    suspend fun execute(student: StudentEntity) {
        repository.addStudent(student)
    }
}