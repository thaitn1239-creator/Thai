package com.example.dataflownavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var tvResultEmail: TextView
    private lateinit var tvResultOtp: TextView
    private lateinit var tvResultPass: TextView

    // Nhận dữ liệu trả về từ các màn hình sau theo cơ chuẩn ActivityResult API
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val email = data?.getStringExtra("email") ?: ""
            val otp = data?.getStringExtra("otp") ?: ""
            val pass = data?.getStringExtra("pass") ?: ""

            // Hiển thị ra khung màu xanh dưới Màn hình 1
            tvResultEmail.text = email
            tvResultOtp.text = otp
            tvResultPass.text = pass
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        edtEmail = findViewById(R.id.edtEmail)
        tvResultEmail = findViewById(R.id.tvResultEmail)
        tvResultOtp = findViewById(R.id.tvResultOtp)
        tvResultPass = findViewById(R.id.tvResultPass)
        val btnNext1 = findViewById<Button>(R.id.btnNext1)

        btnNext1.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, VerifyCodeActivity::class.java).apply {
                putExtra("email", email)
            }
            launcher.launch(intent)
        }
    }
}