package com.example.mobile2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity: AppCompatActivity(

) {
    private lateinit var student: Student

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.apply {
            title = "Student Details"
        }

        student = intent.getParcelableExtra("selected_student", Student::class.java)!!

        Log.d("student: ",student.toString());
        initValues();

        val checkBox: CheckBox = findViewById<CheckBox>(R.id.checkBoxIsChecked);
        checkBox.setOnClickListener {
            student.isChecked = checkBox.isChecked
        }

        findViewById<Button>(R.id.buttonEdit).setOnClickListener {
            openEditStudentPage()
        }


        }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 100) { // 100 means DELETE
            val deletedStudent = data?.getParcelableExtra<Student>("deleted_student")
            deletedStudent?.let {
                Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
                val deleteIntent = Intent().apply {
                    putExtra("deleted_student", student)
                }
                setResult(100, deleteIntent)
                finish()
            }
        } else if (resultCode == 101) { // 101 Means Canceled
            val updatedStudent = data?.getParcelableExtra<Student>("updated_student")
            updatedStudent?.let {
                Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show()
                val updateIntent = Intent().apply {
                    putExtra("updated_student", updatedStudent)
                }
                setResult(101, updateIntent)
                finish()
            }
        }
        else {
            Toast.makeText(this, "Action Canceled!", Toast.LENGTH_SHORT).show()
        }

        initValues()
    }

    private fun openEditStudentPage() {
        val intent = Intent(this, StudentEditActivity::class.java)
        intent.putExtra("selected_student", student)
        startActivityForResult(intent, 80) // TODO: change this
    }

    private fun initValues() {
        findViewById<TextView>(R.id.textViewName).text = "Name: " + student.name;
        findViewById<TextView>(R.id.textViewID).text = "Id: " + student.id;
        findViewById<TextView>(R.id.textViewPhone).text = "Phone: " + student.phone;
        findViewById<TextView>(R.id.textViewAddress).text = "Address: " + student.address;
        findViewById<CheckBox>(R.id.checkBoxIsChecked).isChecked = student.isChecked;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Navigate back
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
