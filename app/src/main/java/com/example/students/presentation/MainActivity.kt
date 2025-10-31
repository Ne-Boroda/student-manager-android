package com.example.students.presentation

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.students.R
import com.example.students.data.Student
import com.example.students.databinding.ActivityMainBinding
import com.example.students.databinding.DialogAddStudentBinding
import com.example.students.presentation.adapter.StudentAdapter
import com.example.students.presentation.viewmodel.StudentViewModel
import com.google.android.material.behavior.SwipeDismissBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentAdapter
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = StudentAdapter(viewModel.students)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.fabAddStudent.setOnClickListener {
            showAddStudentDialog()
        }

        setupSwipeToDelete()
    }

    private fun showAddStudentDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Add New Student")

        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        builder.setPositiveButton("Add") { dialog, which ->
            val name = dialogBinding.nameEditText.text.toString()
            val age = dialogBinding.ageEditText.text.toString().toIntOrNull() ?: 0
            val avgGrade = dialogBinding.avgGradeEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotBlank() && age > 0) {
                val newStudent = Student(name, age, avgGrade)
                viewModel.addStudent(newStudent)
                adapter.notifyDataSetChanged()
            }
        }

        builder.setNegativeButton ("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                viewModel.removeStudent(position)
                adapter.notifyItemRemoved(position)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}
