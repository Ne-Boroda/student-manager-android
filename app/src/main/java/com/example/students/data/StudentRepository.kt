package com.example.students.data

class StudentRepository {
    val students = mutableListOf<Student>(
        Student("Maria", 29, 7.8),
        Student("Max", 23, 5.2),
        Student("John", 19, 9.8)
    )

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(position: Int) {
        students.removeAt(position)
    }
}