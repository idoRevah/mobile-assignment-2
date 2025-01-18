package com.example.mobile2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity: AppCompatActivity(

) {
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        student = intent.getParcelableExtra<Student>("selected_student")!!

    }
}