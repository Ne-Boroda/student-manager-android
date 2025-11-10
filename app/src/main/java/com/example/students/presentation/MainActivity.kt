package com.example.students.presentation

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.students.R
import com.example.students.data.StudentEntity
import com.example.students.databinding.ActivityMainBinding
import com.example.students.databinding.DialogAddStudentBinding
import com.example.students.presentation.adapter.StudentAdapter
import com.example.students.presentation.viewmodel.StudentViewModel
import com.example.students.presentation.viewmodel.StudentViewModelFactory
import com.google.android.material.animation.Positioning
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentAdapter

    private val viewModel: StudentViewModel by viewModels{
        StudentViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = StudentAdapter(
            emptyList(),
            onStudentClick = { student ->
                showEditStudentDialog(student)
            })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.students.collect { students ->
                adapter.students = students
                adapter.notifyDataSetChanged()
            }
        }

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
                viewModel.addStudent(name, age, avgGrade)
            }
        }

        builder.setNegativeButton ("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun showEditStudentDialog(student: StudentEntity) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Edit Student")

        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        dialogBinding.nameEditText.setText(student.name)
        dialogBinding.ageEditText.setText(student.age.toString())
        dialogBinding.avgGradeEditText.setText(student.averageGrade.toString())

        builder.setPositiveButton("Save") { dialog, which ->
            val name = dialogBinding.nameEditText.text.toString()
            val age = dialogBinding.ageEditText.text.toString().toIntOrNull() ?: 0
            val avgGrade = dialogBinding.avgGradeEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotBlank() && age > 0) {
                val updatedStudent = student.copy(
                    name = name,
                    age = age,
                    averageGrade = avgGrade)
                viewModel.updateStudent(updatedStudent)
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
                val student = adapter.students[position]

                viewModel.removeStudent(student)

                showUndoSnackbar(student)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun showUndoSnackbar(student: StudentEntity) {
        val snackbar = Snackbar.make(
            binding.root,
            "Student deleted",
            Snackbar.LENGTH_LONG
        )

        snackbar.setAction("UNDO") {
            viewModel.addStudent(student.name, student.age, student.averageGrade)
        }

        snackbar.show()
    }
}
