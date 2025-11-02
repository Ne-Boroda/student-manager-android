package com.example.students.domain.usecases

import com.example.students.data.Student
import com.example.students.data.StudentRepository

class UpdateStudentUseCase(private val repository: StudentRepository) {
    fun execute (position: Int, student: Student) {
        repository.updateStudent(position, student)
    }
}