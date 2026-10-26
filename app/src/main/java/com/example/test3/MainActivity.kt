package com.example.test3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Liên kết với giao diện activity_start.xml vừa tạo
        setContentView(R.layout.activity_start)

        // Khi bấm vào màn hình Start, chuyển sang màn hình Onboarding (chúng ta sẽ tạo ngay sau đây)
        val rootLayout = findViewById<View>(R.id.rootStartLayout)
        rootLayout.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}