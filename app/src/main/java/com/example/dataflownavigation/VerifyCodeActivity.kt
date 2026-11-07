package com.example.dataflownavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class VerifyCodeActivity : AppCompatActivity() {

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, result.data)
            finish() // Đóng chuỗi truyền ngược về Màn 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)

        val email = intent.getStringExtra("email") ?: ""
        val btnBack2 = findViewById<ImageView>(R.id.btnBack2)
        val btnNext2 = findViewById<Button>(R.id.btnNext2)

        val otpBoxes = listOf(
            findViewById<EditText>(R.id.otp1),
            findViewById<EditText>(R.id.otp2),
            findViewById<EditText>(R.id.otp3),
            findViewById<EditText>(R.id.otp4),
            findViewById<EditText>(R.id.otp5)
        )

        btnBack2.setOnClickListener { finish() }

        btnNext2.setOnClickListener {
            val otp = otpBoxes.joinToString("") { it.text.toString() }
            if (otp.length < 5) {
                Toast.makeText(this, "Vui lòng nhập đủ 5 số OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResetPasswordActivity::class.java).apply {
                putExtra("email", email)
                putExtra("otp", otp)
            }
            launcher.launch(intent)
        }
    }
}