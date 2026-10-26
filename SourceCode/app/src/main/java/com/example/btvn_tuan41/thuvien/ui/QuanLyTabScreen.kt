package com.example.btvn_tuan41.thuvien.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn_tuan41.thuvien.model.Book
import com.example.btvn_tuan41.thuvien.model.Student
import com.example.btvn_tuan41.thuvien.repository.LibraryRepository


@Composable
fun QuanLyTabScreen(
    repository: LibraryRepository,
    refreshTick: Int,
    onRefresh: () -> Unit
) {
    val students = remember { repository.getAllStudents() }
    var currentStudentIndex by remember { mutableIntStateOf(0) }
    val currentStudent = students[currentStudentIndex]
    //lay danh sach sach muon cua sinh vien
    val borrowedBooks = remember(currentStudentIndex, refreshTick) {
        repository.getBooksByStudentId(currentStudent.mssv)
    }
    var showBorrowDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Sinh viên",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        //hang bao ten sinh vien
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .border(1.dp, Color(0xFFD0D5DD), RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "${currentStudent.name} (${currentStudent.className})",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Button(
                onClick = {
                    currentStudentIndex = (currentStudentIndex + 1) % students.size
                },
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2856B6))
            ) {
                Text(text = "Thay đổi", fontSize = 14.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Danh sách sách đã mượn",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE5E7EB), RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            if (borrowedBooks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Sinh viên chưa mượn cuốn sách nào.",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(borrowedBooks) { book ->
                        BorrowedBookItem(
                            book = book,
                            onReturn = {
                                repository.returnBook(currentStudent.mssv, book.id)
                                onRefresh()
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { showBorrowDialog = true },
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2856B6))
        ) {
            Text(text = "Thêm sách", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
    }
    //dialog
    if (showBorrowDialog) {
        BorrowBookDialog(
            student = currentStudent,
            repository = repository,
            onDismiss = { showBorrowDialog = false },
            onBookSelected = { bookId ->
                repository.borrowBook(currentStudent.mssv, bookId)
                onRefresh()
                showBorrowDialog = false
            }
        )
    }
}
//danh dau muon
@Composable
fun BorrowedBookItem(
    book: Book,
    onReturn: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(Color(0xFFB71C1C), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Borrowed",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Tác giả: ${book.author} • ${book.category.displayName}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            //nut tra sach
            IconButton(
                onClick = onReturn,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Trả sách",
                    tint = Color.Red.copy(alpha = 0.7f)
                )
            }
        }
    }
}
// Dialog chon sach muon
@Composable
fun BorrowBookDialog(
    student: Student,
    repository: LibraryRepository,
    onDismiss: () -> Unit,
    onBookSelected: (String) -> Unit
) {
    val availableBooks = repository.getAllBooks().filter { book ->
        !student.hasBorrowedBook(book.id) && book.isAvailable
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Chọn sách cho ${student.name}", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        },
        text = {
            if (availableBooks.isEmpty()) {
                Text("Hiện không còn sách khả dụng trong kho để mượn thêm!")
            } else {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 350.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(availableBooks) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onBookSelected(book.id) },
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = book.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                                Text(
                                    text = "${book.author} (${book.publishedYear}) - Còn ${book.availableCopies} cuốn",
                                    fontSize = 12.sp,
                                    color = Color(0xFF027A48)
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}

