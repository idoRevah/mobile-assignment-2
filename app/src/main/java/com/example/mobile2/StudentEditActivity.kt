package com.example.mobile2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mobile2.databinding.ActivityEditStudentBinding
import com.google.android.material.textfield.TextInputEditText

class StudentEditActivity: AppCompatActivity() {
    private lateinit var student: Student
    private lateinit var binding: ActivityEditStudentBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_student)

        student = intent.getParcelableExtra("selected_student", Student::class.java)
            ?: throw IllegalArgumentException("Student data missing")

        binding.student = student;

        binding.buttonSave.setOnClickListener {

            saveStudentChanges()
        }

        binding.buttonCancel.setOnClickListener {
            var resultIntent = Intent();
            setResult(0, resultIntent)
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            deleteStudent()
        }


        val checkBox: CheckBox = findViewById<CheckBox>(R.id.checkBoxIsChecked);
        checkBox.isChecked = student.isChecked;
        checkBox.setOnClickListener {
            student.isChecked = checkBox.isChecked
        }
    }

    private fun saveStudentChanges() {
        val resultIntent = Intent().apply {
            putExtra("updated_student", student)  // 📤 Send updated student back
        }
        setResult(101, resultIntent)
        finish()
    }


    private fun deleteStudent() {
        val resultIntent = Intent().apply {
            putExtra("deleted_student", student)
        }
        setResult(100, resultIntent)
        finish()
    }



}