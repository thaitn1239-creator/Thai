package com.example.dataflownavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val email = intent.getStringExtra("email") ?: ""
        val otp = intent.getStringExtra("otp") ?: ""
        val pass = intent.getStringExtra("pass") ?: ""

        val edtConfirmEmail = findViewById<EditText>(R.id.edtConfirmEmail)
        val edtConfirmOtp = findViewById<EditText>(R.id.edtConfirmOtp)
        val edtConfirmPass = findViewById<EditText>(R.id.edtConfirmPass)
        val btnBack4 = findViewById<ImageView>(R.id.btnBack4)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Hiển thị thông tin nhận được lên các ô đọc-ghi
        edtConfirmEmail.setText(email)
        edtConfirmOtp.setText(otp)
        edtConfirmPass.setText(pass)

        btnBack4.setOnClickListener { finish() }

        // Bấm Submit để gói data trả ngược về Màn hình 1
        btnSubmit.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("email", email)
                putExtra("otp", otp)
                putExtra("pass", pass)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finishAffinity() // Hoặc finish() liên tục để quay về Màn hình 1 sạch sẽ
        }
    }
}