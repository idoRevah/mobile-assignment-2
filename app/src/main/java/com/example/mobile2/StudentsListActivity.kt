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
        setContentView(R.layout.activity_students_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        val fabAddStudent = findViewById<FloatingActionButton>(R.id.fabAddStudent)
        studentsList.addAll(listOf(
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "001", name = "Alice Johnson", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            ))
        Log.d("StudentsListActivity", "Student list size: ${studentsList.size}")

        // TODO: Initialize RecyclerView
        studentAdapter = StudentAdapter(studentsList) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
//            intent.putExtra("selected_student", student)
            startActivity(intent)

        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // TODO: add fab actions details...
//        fabAddStudent.setOnClickListener {
//            val intent = Intent(this, NewStudentActivity::class.java)
//            startActivityForResult(intent, 1)
//    }
    }
    // ???
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newStudent = data?.getSerializableExtra("new_student") as? Student
            newStudent?.let {
                studentsList.add(it)
                studentAdapter.notifyItemInserted(studentsList.size - 1)
            }
        }
    }
}