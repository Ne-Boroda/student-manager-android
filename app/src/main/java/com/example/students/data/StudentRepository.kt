package com.example.students.data

class StudentRepository {
    fun getStudents(): List<Student> {
        return listOf(
            Student("Maria", 29, 7.8),
            Student("Max", 23, 5.2),
            Student("John", 19, 9.8)
        )
    }
}