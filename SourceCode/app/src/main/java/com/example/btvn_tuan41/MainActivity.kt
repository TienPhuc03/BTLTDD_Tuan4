package com.example.btvn_tuan41

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn_tuan41.smarttasks.ui.SmartTasksNav
import com.example.btvn_tuan41.thuvien.ui.LibraryMainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppAssignmentChooser()
            }
        }
    }

    // Đặt hàm giao diện ngay bên trong class MainActivity
    @Composable
    fun AppAssignmentChooser() {
        var selectedApp by remember { mutableIntStateOf(0) }

        when (selectedApp) {
            1 -> LibraryMainScreen()
            2 -> SmartTasksNav(onFinished = { selectedApp = 0 })
            else -> {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF9FAFB)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "BÀI TẬP TUẦN 4 - UTH",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1D2939)
                        )
                        Text(
                            text = "Thực hành Navigation + OOP",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(36.dp))

                        // Nút chọn Bài 1: Quản lý Thư viện
                        Button(
                            onClick = { selectedApp = 1 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2856B6))
                        ) {
                            Text("Bài 1: Quản lý Thư viện", fontSize = 16.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Nút chọn Bài 2: SmartTasks
                        Button(
                            onClick = { selectedApp = 2 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29B6F6))
                        ) {
                            Text("Bài 2: SmartTasks (Data Flow)", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}