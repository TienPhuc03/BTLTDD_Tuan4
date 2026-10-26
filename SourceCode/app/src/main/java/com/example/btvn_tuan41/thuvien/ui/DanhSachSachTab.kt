package com.example.btvn_tuan41.thuvien.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn_tuan41.thuvien.model.BookCategory
import com.example.btvn_tuan41.thuvien.repository.LibraryRepository

@Composable
fun DanhSachSachTab(repository: LibraryRepository) {
    val allBooks = remember { repository.getAllBooks() }
    var selectedCategory by remember { mutableStateOf<BookCategory?>(null) }
    val filteredBooks = remember(selectedCategory) {
        if (selectedCategory == null) allBooks
        else allBooks.filter { it.category == selectedCategory }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Kho Sách Thư Viện",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2856B6)
        )
        Text(
            text = "Tổng cộng: ${allBooks.size} đầu sách phong phú",
            fontSize = 13.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        // Thanh chọn Thể loại (Chips)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("Tất cả") }
                )
            }
            items(BookCategory.values()) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category.displayName) }
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        // Danh sách sách
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(filteredBooks) { book ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "[${book.id}] ${book.title}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                            // Trạng thái còn/hết
                            val statusColor = if (book.isAvailable) Color(0xFF12B76A) else Color(0xFFF04438)
                            val statusText = if (book.isAvailable) "Còn sách" else "Đã hết"
                            Text(
                                text = statusText,
                                color = statusColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .background(statusColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Tác giả: ${book.author} • Năm XB: ${book.publishedYear}",
                            fontSize = 13.sp,
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Khả dụng: ${book.availableCopies}/${book.totalCopies} cuốn • ${book.category.displayName}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
