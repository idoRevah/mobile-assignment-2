package com.example.mobile2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class StudentDetailsActivity: AppCompatActivity(

) {
    private lateinit var student: Student

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        student = intent.getParcelableExtra("selected_student", Student::class.java)!!

        Log.d("student: ",student.toString());
        InitValues();

        val checkBox: CheckBox = findViewById<CheckBox>(R.id.checkBoxIsChecked);
        checkBox.setOnClickListener {
            checkBox.isChecked = !checkBox.isChecked
            student.isChecked = checkBox.isChecked
        }


    }

    fun InitValues() {
        findViewById<TextView>(R.id.textViewName).text = "Name: " + student.name;
        findViewById<TextView>(R.id.textViewID).text = "Id: " + student.id;
        findViewById<TextView>(R.id.textViewPhone).text = "Phone: " + student.phone;
        findViewById<TextView>(R.id.textViewAddress).text = "Address: " + student.address;
        findViewById<CheckBox>(R.id.checkBoxIsChecked).isChecked = student.isChecked;
    }
}
