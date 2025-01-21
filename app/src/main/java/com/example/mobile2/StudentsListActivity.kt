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
        supportActionBar?.apply {
            title = "All Students"
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        val fabAddStudent = findViewById<FloatingActionButton>(R.id.fabAddStudent)
        studentsList.addAll(listOf(
            Student(id = "000", name = "Israel Israeli", isChecked = true,phone = "0509534647", address = "Haim Cohen"),
            Student(id = "111", name = "Alice Johnson", isChecked = false,phone = "0504560064", address = "HaZamir Hod Hasharon"),
            Student(
                id = "001",
                name = "Luke Skywalker",
                isChecked = false,
                phone = "1234567890",
                address = "Tatooine"
            ),
            Student(
                id = "002",
                name = "Bruce Wayne",
                isChecked = true,
                phone = "9876543210",
                address = "Wayne Manor, Gotham City"
            ),
            Student(
                id = "003",
                name = "Clark Kent",
                isChecked = false,
                phone = "5558889999",
                address = "Smallville, Kansas"
            ),
            Student(
                id = "004",
                name = "Tony Stark",
                isChecked = true,
                phone = "8885553333",
                address = "Stark Tower, New York"
            ),
            Student(
                id = "005",
                name = "Darth Vader",
                isChecked = true,
                phone = "6660006666",
                address = "Death Star, Space"
            ),
            Student(
                id = "006",
                name = "Sherlock Holmes",
                isChecked = false,
                phone = "2211899333",
                address = "221B Baker Street, London"
            ),
            Student(
                id = "007",
                name = "James Bond",
                isChecked = true,
                phone = "0070070070",
                address = "Everywhere around the globe"
            ),
            Student(
                id = "008",
                name = "Harry Potter",
                isChecked = false,
                phone = "9991110000",
                address = "4 Privet Drive, Surrey"
            ),
            Student(
                id = "009",
                name = "Frodo Baggins",
                isChecked = true,
                phone = "1112223333",
                address = "Bag End, Hobbiton"
            ),
            Student(
                id = "010",
                name = "Willy Wonka",
                isChecked = false,
                phone = "5551234567",
                address = "Chocolate Factory, Candyland"
            )
            ))
        Log.d("StudentsListActivity", "Student list size: ${studentsList.size}")

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


    @Deprecated("Deprecated in Java")
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

    private fun updateStudent(stud: Student) {
        val index = studentsList.indexOfFirst { it.id == stud.id }

        if (index != -1) {
            studentsList[index] = stud
            studentAdapter.notifyItemChanged(index)
        }
    }

    private fun deleteStudent(stud: Student) {
        val index = studentsList.indexOfFirst { it.id == stud.id }
        Log.d("Delete Triggered, stud in index of",index.toString())
        if (index != -1) {
            studentsList.removeAt(index)
            studentAdapter.notifyItemRemoved(index)
            Log.d("Delete Triggered, new list:",studentsList.toString())
        }
    }
}