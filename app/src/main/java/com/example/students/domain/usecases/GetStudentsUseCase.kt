package com.example.students.domain.usecases

import com.example.students.data.Student
import com.example.students.data.StudentRepository

class GetStudentsUseCase(private val repository: StudentRepository) {
    fun execute(): List<Student> {
        return repository.students
    }
}