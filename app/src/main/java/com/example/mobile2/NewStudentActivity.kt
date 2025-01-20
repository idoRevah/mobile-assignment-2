package com.example.mobile2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewStudentActivity: AppCompatActivity() {
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        student = Student(id="a", address = "sdf", phone = "sf", isChecked = false, name="skdlf", profileImageId = 32 )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val saveStudentButton = findViewById<Button>(R.id.buttonSave)

        saveStudentButton.setOnClickListener{
            Log.d("intent: clicked", "naaa")
            saveStud()
        }

//        val intent = Intent(this, StudentDetailsActivity::class.java)
//        intent.putExtra("selected_student", student)
    }

    private fun saveStud() {
        Log.d("intent: clicked", "naaa222")
        student.isChecked = findViewById<CheckBox>(R.id.checkboxIsChecked).isChecked
        student.id = findViewById<EditText>(R.id.editID).text.toString()
        student.name = findViewById<EditText>(R.id.editName).text.toString()
        student.phone = findViewById<EditText>(R.id.editPhone).text.toString()
        student.address = findViewById<EditText>(R.id.editAddress).text.toString()
        Log.d("intent: trying to save student0", student.toString())
        val resultIntent = Intent().apply {
            putExtra("new_student", student)
        }
        Log.d("intent: trying to save student1", student.toString())
        setResult(10, resultIntent)
        finish()
    }
}