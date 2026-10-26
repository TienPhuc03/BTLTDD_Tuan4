package com.example.btvn_tuan41.thuvien.model

data class Student(
    val mssv : String,
    val name: String,
    val className: String,
    val borrowedBookIds: MutableList<String> = mutableListOf()
) {
    //kiem tra xe sach da ca ai muon chua
    fun hasBorrowedBook(bookId: String): Boolean = borrowedBookIds.contains(bookId)

    fun borrow (bookId: String) {
        if (!hasBorrowedBook(bookId)) {
            borrowedBookIds.add(bookId)
        }

    }

    fun returnBook(bookId: String) {
        borrowedBookIds.remove(bookId)
    }


}