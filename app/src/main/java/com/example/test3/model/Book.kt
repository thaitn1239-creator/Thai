package com.example.test3.model

class Book(val bookId: String, val title: String, private var _isAvailable: Boolean = true) {

    // Tính Đóng gói: Ẩn trạng thái, cung cấp getter an toàn
    val isAvailable: Boolean
        get() = _isAvailable

    fun markAsBorrowed() {
        _isAvailable = false
    }

    fun markAsReturned() {
        _isAvailable = true
    }
}