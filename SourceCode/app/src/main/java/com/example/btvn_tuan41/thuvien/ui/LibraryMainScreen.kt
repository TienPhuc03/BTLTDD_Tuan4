package com.example.btvn_tuan41.thuvien.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.btvn_tuan41.thuvien.repository.LibraryRepository


@Composable
fun LibraryMainScreen() {
    val repository = remember { LibraryRepository() }
    var selectedTab by remember { mutableIntStateOf(0) }
    //refresh khi thuc hien muon tra
    var refreshTick by remember { mutableIntStateOf(0) }

    Scaffold(
      bottomBar = {
          NavigationBar(
              containerColor = Color.White
          ) {
              NavigationBarItem(
                  selected = selectedTab == 0,
                  onClick = { selectedTab = 0},
                  icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý")},
                  label = { Text("Quản lý")},
                  colors = NavigationBarItemDefaults.colors(
                      selectedIconColor = Color.Blue,
                      selectedTextColor = Color.Blue,
                      unselectedIconColor = Color.Gray,
                      unselectedTextColor = Color.Gray
                  )
              )
              NavigationBarItem(
                  selected = selectedTab == 1,
                  onClick = { selectedTab = 1 },
                  icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "DS Sách") },
                  label = { Text("DS Sách") },
                  colors = NavigationBarItemDefaults.colors(
                      selectedIconColor = Color.Blue,
                      selectedTextColor = Color.Blue,
                      unselectedIconColor = Color.Gray,
                      unselectedTextColor = Color.Gray
                  )
              )
              NavigationBarItem(
                  selected = selectedTab == 2,
                  onClick = { selectedTab = 2 },
                  icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
                  label = { Text("Sinh viên") },
                  colors = NavigationBarItemDefaults.colors(
                      selectedIconColor = Color(0xFF2856B6),
                      selectedTextColor = Color(0xFF2856B6),
                      unselectedIconColor = Color.Gray,
                      unselectedTextColor = Color.Gray
                  )
              )
          }
      }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when(selectedTab){
                0 -> QuanLyTabScreen(
                    repository = repository,
                    refreshTick = refreshTick,
                    onRefresh = { refreshTick++ }
                )
                1 -> DanhSachSachTab(
                    repository = repository
                )
                2 -> SinhVienTab(
                    repository = repository
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LibraryMainScreenPreview() {
    MaterialTheme {
        LibraryMainScreen()
    }
}
