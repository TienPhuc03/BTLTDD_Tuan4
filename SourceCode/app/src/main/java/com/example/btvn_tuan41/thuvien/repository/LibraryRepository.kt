package com.example.btvn_tuan41.thuvien.repository

import com.example.btvn_tuan41.thuvien.model.Book
import com.example.btvn_tuan41.thuvien.model.BookCategory
import com.example.btvn_tuan41.thuvien.model.Student

class LibraryRepository {

    private val books = mutableListOf(
        Book(
            id = "BK001",
            title = "Lập trình Android với Jetpack Compose",
            author = "Google Developers",
            category = BookCategory.CONG_NGHE_THONG_TIN,
            publishedYear = 2023,
            totalCopies = 5
        ),
        Book(
            id = "BK002",
            title = "Clean Code - Mã Sạch",
            author = "Robert C. Martin",
            category = BookCategory.CONG_NGHE_THONG_TIN,
            publishedYear = 2020,
            totalCopies = 4
        ),
        Book(
            id = "BK003",
            title = "Cấu trúc dữ liệu và Giải thuật",
            author = "Nguyễn Đức Nghĩa",
            category = BookCategory.KHOA_HOC_MAY_TINH,
            publishedYear = 2021,
            totalCopies = 6
        ),
        Book(
            id = "BK004",
            title = "Trí tuệ nhân tạo trong Kỷ nguyên số",
            author = "Stuart Russell & Peter Norvig",
            category = BookCategory.KHOA_HOC_MAY_TINH,
            publishedYear  = 2022,
            totalCopies = 3
        ),
        Book(
            id = "BK005",
            title = "Thiết kế Kiến trúc Hệ thống Microservices",
            author = "Sam Newman",
            category = BookCategory.CONG_NGHE_THONG_TIN,
            publishedYear = 2023,
            totalCopies = 3
        ),
        Book(
            id = "BK006",
            title = "Kinh tế học vĩ mô ứng dụng",
            author = "N. Gregory Mankiw",
            category = BookCategory.KINH_TE_QUAN_TRI,
            publishedYear = 2021,
            totalCopies = 5
        ),
        Book(
            id = "BK007",
            title = "Đắc Nhân Tâm",
            author = "Dale Carnegie",
            category = BookCategory.KY_NANG_SONG,
            publishedYear = 2019,
            totalCopies = 8
        ),
        Book(
            id = "BK008",
            title = "Nhà Giả Kim",
            author = "Paulo Coelho",
            category = BookCategory.VAN_HOC,
            publishedYear = 2018,
            totalCopies = 4
        )
    )
    // Danh sách sinh viên mẫu
    private val students = mutableListOf(
        Student(mssv = "SV01", name = "Nguyễn Trần Tiến Phúc", className = "K24 - CNTT01"),
        Student(mssv = "SV02", name = "Nguyễn Trọng Nhân", className = "K24 - CNTT02"),
        Student(mssv = "SV03", name = "Lê Hữu Minh Phú", className = "K24 - KHMT")
    )
    init {
        // Mượn mặc định một vài cuốn ban đầu cho SV01 theo slide mô phỏng
        borrowBook(studentId = "SV01", bookId = "BK001")
        borrowBook(studentId = "SV01", bookId = "BK002")
    }
    //ham lay du lieu
    fun getAllBooks(): List<Book> = books.toList()
    fun getBookById(bookId: String): Book? = books.find { it.id == bookId }

    fun getAllStudents(): List<Student> = students.toList()
    fun getStudentById(studentId: String): Student? = students.find { it.mssv == studentId }

    fun getBooksByStudentId(studentId: String): List<Book> {
        val student = getStudentById(studentId) ?: return emptyList()
        return student.borrowedBookIds.mapNotNull { getBookById(it) }
    }

    //muon sach
    fun borrowBook(studentId: String, bookId: String): Boolean{
        val student = getStudentById(studentId) ?: return false
        val book = getBookById(bookId) ?: return false

        if (student.hasBorrowedBook(bookId)) return false
        if (!book.isAvailable) return false

        book.borrowOne()
        student.borrow(bookId)
        return true
    }

    //tra sach
    fun returnBook (studentId: String, bookId: String): Boolean {
        val student = getStudentById(studentId) ?: return false
        val book = getBookById(bookId) ?: return false

        if (!student.hasBorrowedBook(bookId)) return false

        book.returnOne()
        student.returnBook(bookId)
        return true
    }

}
