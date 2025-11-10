package com.example.students.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.students.data.StudentEntity
import com.example.students.databinding.ItemStudentBinding

class StudentAdapter(
    internal var students: List<StudentEntity>,
    private val onStudentClick: (StudentEntity) -> Unit
    ) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // 1. Метод: создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding, onStudentClick)
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

    class StudentViewHolder(
        private val binding: ItemStudentBinding,
        private val onStudentClick: (StudentEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: StudentEntity) {
            binding.nameText.text = student.name
            binding.ageText.text = "Age: ${student.age}"
            binding.avgGradeText.text = "Average Grade: ${student.averageGrade}"

            binding.root.setOnClickListener {
                onStudentClick(student)
            }
        }
    }
}