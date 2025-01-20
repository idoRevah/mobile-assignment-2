package com.example.mobile2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter ( private val studentList: MutableList<Student>,   // Data source (list of students)
                       private val onItemClick: (Student) -> Unit       // Click event for each item
) : RecyclerView.Adapter<StudentAdapter.StudentItemHolder>() {



    inner class StudentItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewID: TextView = itemView.findViewById(R.id.textViewID)
        val checkBoxActive: CheckBox = itemView.findViewById(R.id.checkBoxIsChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentItemHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.count()
    }

    override fun onBindViewHolder(holder: StudentItemHolder, position: Int) {
        val student = studentList[position]

        holder.textViewName.text = student.name
        holder.textViewID.text = "ID: ${student.id}"
//        holder.imageViewProfile.setImageResource(student.profileImageId)
        holder.checkBoxActive.isChecked = student.isChecked
        holder.checkBoxActive.setOnCheckedChangeListener(null)
        holder.checkBoxActive.isChecked = student.isChecked
        holder.checkBoxActive.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.itemView.setOnClickListener {
            onItemClick(student)
        }
    }

}