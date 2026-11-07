package com.example.uthtodo // Đổi lại đúng package của bạn

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgProfile = findViewById<ImageView>(R.id.imgProfile)
        val lvTasks = findViewById<ListView>(R.id.lvTasks)

        val taskList = arrayOf(
            "Tập thể dục 30 phút - 06:30",
            "Nộp bài tập Lập trình di động - 09:00",
            "Ôn chương 3 - Cấu trúc dữ liệu - 14:00",
            "Đi chợ mua đồ cho tuần - 17:30"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        lvTasks.adapter = adapter

        imgProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}