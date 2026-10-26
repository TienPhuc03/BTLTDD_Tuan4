package com.example.btvn_tuan41.smarttasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//man hinh 1
@Composable
fun EmailScreen(
    onNext: (String) -> Unit
) {
    var email by remember { mutableStateOf("uth@gmail.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        UthHeaderLogo()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Quên mật khẩu?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Nhập Email của bạn để chúng tôi gửi lại mật khẩu.",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email của bạn", color = Color.LightGray) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFF29B6F6)
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = { if (email.isNotBlank()) onNext(email) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29B6F6))
        ) {
            Text("Tiếp theo", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

//man hinh 2 (otp)
@Composable
fun OtpScreen(
    onBack: () -> Unit,
    onNext: (String) -> Unit
) {
    var otp1 by remember { mutableStateOf("1") }
    var otp2 by remember { mutableStateOf("2") }
    var otp3 by remember { mutableStateOf("3") }
    var otp4 by remember { mutableStateOf("4") }
    var otp5 by remember { mutableStateOf("5") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBackButton(onBack = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        UthHeaderLogo()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Verify Code",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Nhập mã OTP đã được gửi đến email của bạn",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        //5 box chua so
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OtpDigitBox(digit = otp1) { if (it.length <= 1) otp1 = it }
            OtpDigitBox(digit = otp2) { if (it.length <= 1) otp2 = it }
            OtpDigitBox(digit = otp3) { if (it.length <= 1) otp3 = it }
            OtpDigitBox(digit = otp4) { if (it.length <= 1) otp4 = it }
            OtpDigitBox(digit = otp5) { if (it.length <= 1) otp5 = it }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val fullOtp = "$otp1$otp2$otp3$otp4$otp5"
                onNext(fullOtp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29B6F6))
        ) {
            Text("Tiếp theo", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

//man hinh 3
@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit,
    onNext: (String) -> Unit
) {
    var password by remember { mutableStateOf("12345678") }
    var confirmPassword by remember { mutableStateOf("12345678") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBackButton(onBack = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        UthHeaderLogo()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Tạo mật khẩu mới",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Mật khẩu mới phải khác mật khẩu cũ",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Mât khẩu mới", color = Color.LightGray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFF29B6F6)
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Xác nhận mật khẩu", color = Color.LightGray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFF29B6F6)
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                if (password.isNotBlank() && password == confirmPassword) {
                    onNext(password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29B6F6))
        ) {
            Text("Next", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

//man hinh 4
@Composable
fun ConfirmScreen(
    email: String,
    otp: String,
    pass: String,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBackButton(onBack = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        UthHeaderLogo()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Xác nhận thông tin",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Chúng tôi sẽ giúp bạn",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        //hien thi email truyen tu man hinh 1
        ConfirmItemRow(icon = Icons.Default.Email, value = email)
        Spacer(modifier = Modifier.height(14.dp))

        //hien thi otp truyen tu man hinh 2
        ConfirmItemRow(icon = Icons.Default.Password, value = otp)
        Spacer(modifier = Modifier.height(14.dp))

        //hien thi pass truyen tu man hinh 3
        ConfirmItemRow(icon = Icons.Default.Lock, value = "•".repeat(pass.length.coerceAtLeast(8)))

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29B6F6))
        ) {
            Text("Xác nhận", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

//component phu
@Composable
fun UthHeaderLogo() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "UTH",
            color = Color(0xFF00796B),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "UNIVERSITY OF TRANSPORT HOCHIMINH CITY",
            color = Color(0xFFD32F2F),
            fontSize = 7.5.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "SmartTasks",
            color = Color(0xFF29B6F6),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TopBackButton(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFF29B6F6), CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun OtpDigitBox(digit: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color(0xFFF3F4F6), RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = digit,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        )
    }
}

@Composable
fun ConfirmItemRow(icon: ImageVector, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFF9FAFB), RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(10.dp))
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = value, fontSize = 14.sp, color = Color.Black)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "1. Email Screen")
@Composable
fun EmailScreenPreview() {
    EmailScreen(onNext = {})
}

@Preview(showBackground = true, showSystemUi = true, name = "2. OTP Screen")
@Composable
fun OtpScreenPreview() {
    OtpScreen(onBack = {}, onNext = {})
}

@Preview(showBackground = true, showSystemUi = true, name = "3. Reset Pass Screen")
@Composable
fun ResetPasswordScreenPreview() {
    ResetPasswordScreen(onBack = {}, onNext = {})
}

@Preview(showBackground = true, showSystemUi = true, name = "4. Confirm Screen")
@Composable
fun ConfirmScreenPreview() {
    ConfirmScreen(
        email = "uth@gmail.com",
        otp = "12345",
        pass = "password123",
        onBack = {},
        onSubmit = {}
    )
}