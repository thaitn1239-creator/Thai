package com.example.test3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.test3.model.OnboardingItem

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: Button
    private lateinit var btnSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)

        // 1. Tạo danh sách dữ liệu theo đúng chuẩn OOP (Không viết cứng trực tiếp trên giao diện)
        val onboardingList = listOf(
            OnboardingItem(
                "Mọi việc, gọn trong một chỗ",
                "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào."
            ),
            OnboardingItem(
                "Chia theo Học tập và Cuộc sống",
                "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì."
            ),
            OnboardingItem(
                "Nhắc đúng lúc",
                "Bật thông báo để UTH Todo nhắc bạn trước hạn chót."
            )
        )

        // 2. Gắn Adapter vào ViewPager2
        val adapter = OnboardingAdapter(onboardingList)
        viewPager.adapter = adapter

        // 3. Xử lý nút "Tiếp tục"
        btnNext.setOnClickListener {
            if (viewPager.currentItem < onboardingList.size - 1) {
                // Chuyển sang trang kế tiếp
                viewPager.currentItem += 1
            } else {
                // Đã đến trang cuối cùng -> Chuyển sang màn hình chính của ứng dụng
                navigateToMain()
            }
        }

        // 4. Xử lý nút "Bỏ qua" ở góc trên
        btnSkip.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        // Chuyển sang màn hình chính (hoặc làm bước tiếp theo của bài tập)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}