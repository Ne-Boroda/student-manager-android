package com.example.students.domain.usecases

import com.example.students.data.StudentRepository
import com.example.students.data.Student

class AddStudentUseCase(private val repository: StudentRepository) {
    fun execute(student: Student) {
        repository.addStudent(student)
    }
}