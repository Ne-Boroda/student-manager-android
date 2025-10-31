package com.example.students.domain.usecases

import com.example.students.data.Student
import com.example.students.data.StudentRepository

class RestoreStudentUseCase(private val repository: StudentRepository) {
    fun execute(student: Student, position: Int) {
            repository.restoreStudent(student, position)
    }
}