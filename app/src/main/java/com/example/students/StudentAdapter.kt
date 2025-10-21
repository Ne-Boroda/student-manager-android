package com.example.students

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.students.databinding.ItemStudentBinding

class StudentAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // 1. Метод: создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    // 2. Метод: привязка данных к ViewHolder
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    // 3. Метод: количество элементов
    override fun getItemCount(): Int {
        return students.size
    }

    class StudentViewHolder(private val binding: ItemStudentBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.nameText.text = student.name
            binding.ageText.text = "Age: ${student.age}"
            binding.avgGradeText.text = "Average Grade: ${student.averageGrade}"
        }
    }
}