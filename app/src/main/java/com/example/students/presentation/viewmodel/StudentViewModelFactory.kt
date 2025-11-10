package com.example.students.presentation.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.students.data.StudentRepository

class StudentViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            val repository = StudentRepository(context)
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}