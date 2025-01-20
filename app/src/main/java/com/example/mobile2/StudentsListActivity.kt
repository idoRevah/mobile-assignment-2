package com.example.mobile2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity: AppCompatActivity() {

    private val studentsList = mutableListOf<Student>()
    private lateinit var studentAdapter: StudentAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setContentView(R.layout.activity_students_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        val fabAddStudent = findViewById<FloatingActionButton>(R.id.fabAddStudent)
        studentsList.addAll(listOf(
            Student(id = "002", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "005", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            ))
        Log.d("StudentsListActivity", "Student list size: ${studentsList.size}")

        // TODO: Initialize RecyclerView
        studentAdapter = StudentAdapter(studentsList) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("selected_student", student)
            startActivityForResult(intent, 0)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        fabAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivityForResult(intent, 0)
    }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("intent: somethingTriggered", resultCode.toString())
        if (resultCode == 100) { // DELETION
            val deletedStudent = data?.getParcelableExtra<Student>("deleted_student")!!
            Log.d("Delete Triggered",deletedStudent.toString())
            deleteStudent(deletedStudent)
        } else if (resultCode == 101) { // EDIT
            val updatedStudent = data?.getParcelableExtra<Student>("updated_student")!!
            Log.d("Update Triggered", updatedStudent.toString())
            updateStudent(updatedStudent)
        } else if (resultCode == 10) { // New STUDENT
            val newStudent = data?.getParcelableExtra<Student>("new_student")!!
            Log.d("New Student Trigger", newStudent.toString())
            studentsList.add(newStudent)
            studentAdapter.notifyItemInserted(studentsList.size - 1)
            Log.d("Done adding new student", studentsList[studentsList.size - 1].toString())
        }
    }

    fun updateStudent(stud: Student) {
        val index = studentsList.indexOfFirst { it.id == stud.id }

        if (index != -1) {
            studentsList[index] = stud
            studentAdapter.notifyItemChanged(index)
        }
    }

    fun deleteStudent(stud: Student) {
        val index = studentsList.indexOfFirst { it.id == stud.id }
        Log.d("Delete Triggered, stud in index of",index.toString())
        if (index != -1) {
            studentsList.removeAt(index)
            studentAdapter.notifyItemRemoved(index)
            Log.d("Delete Triggered, new list:",studentsList.toString())
        }
    }
}