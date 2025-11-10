package com.example.students.domain.usecases

import com.example.students.data.StudentEntity
import com.example.students.data.StudentRepository
import kotlinx.coroutines.flow.Flow

class GetStudentsUseCase(private val repository: StudentRepository) {
    fun execute(): Flow<List<StudentEntity>> {
        return repository.students
    }
}