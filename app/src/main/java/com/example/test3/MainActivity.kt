package com.example.test3

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test3.model.Book
import com.example.test3.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var student: Student
    private lateinit var bookList: MutableList<Book>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // Khởi tạo đối tượng sinh viên
        student = Student("SV01", "Nguyen Van A", "040206001239")

        // Khởi tạo danh sách sách mẫu
        bookList = mutableListOf(
            Book("B1", "Lập trình Android nâng cao"),
            Book("B2", "Cấu trúc dữ liệu và giải thuật"),
            Book("B3", "Trí tuệ nhân tạo cơ bản")
        )

        val edtName = findViewById<EditText>(R.id.edtStudentName)
        val listView = findViewById<ListView>(R.id.listViewBooks)
        val btnAction = findViewById<Button>(R.id.btnAddOrBorrow)

        edtName.setText(student.name)

        // Hiển thị danh sách lên ListView với kiểu chọn nhiều dòng (checkbox)
        val bookTitles = bookList.map { "${it.title} (${if (it.isAvailable) "Còn sách" else "Đã mượn"})" }.toMutableList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, bookTitles)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView.adapter = adapter

        // Xử lý khi bấm nút Mượn sách
        btnAction.setOnClickListener {
            val checkedPositions = listView.checkedItemPositions
            var count = 0

            for (i in 0 until bookList.size) {
                if (checkedPositions.get(i)) {
                    val book = bookList[i]
                    if (book.isAvailable) {
                        student.borrowBook(book)
                        count++
                    }
                }
            }

            if (count > 0) {
                Toast.makeText(this, "${student.name} đã mượn thành công $count cuốn sách!", Toast.LENGTH_SHORT).show()
                // Cập nhật lại giao diện
                val updatedTitles = bookList.map { "${it.title} (${if (it.isAvailable) "Còn sách" else "Đã mượn"})" }
                adapter.clear()
                adapter.addAll(updatedTitles)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Vui lòng chọn sách chưa mượn!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}