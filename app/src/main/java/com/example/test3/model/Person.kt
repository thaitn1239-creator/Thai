package com.example.test3.model

// Tính Trừu tượng: Lớp cha định nghĩa khung chung
abstract class Person(val id: String, var name: String) {
    abstract fun getRole(): String
}

// Tính Kế thừa: Student kế thừa từ Person
class Student(id: String, name: String, val studentCode: String) : Person(id, name) {
    // Tính Đóng gói: Quản lý danh sách sách đã mượn an toàn bên trong
    private val _borrowedBooks: MutableList<Book> = mutableListOf()
    val borrowedBooks: List<Book> get() = _borrowedBooks

    fun borrowBook(book: Book) {
        if (book.isAvailable) {
            book.markAsBorrowed()
            _borrowedBooks.add(book)
        }
    }

    override fun getRole(): String {
        return "Sinh viên"
    }
}