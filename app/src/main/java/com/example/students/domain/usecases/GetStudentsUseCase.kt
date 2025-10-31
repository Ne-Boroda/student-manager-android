package com.example.students.domain.usecases

import androidx.collection.MutableObjectList
import com.example.students.data.Student
import com.example.students.data.StudentRepository

class GetStudentsUseCase(private val repository: StudentRepository) {
    fun execute(): MutableList<Student> {
        return repository.students
    }
}