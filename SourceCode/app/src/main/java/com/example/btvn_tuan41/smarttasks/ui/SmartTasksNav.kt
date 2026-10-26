package com.example.btvn_tuan41.smarttasks.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvn_tuan41.smarttasks.model.PasswordState

@Composable
fun SmartTasksNav(
    onFinished: () -> Unit = {}
) {
    val navController = rememberNavController()
    //luu du lieu trong state
    val state = remember { PasswordState() }

    NavHost(
        navController = navController,
        startDestination = "email_step"
    ) {
        //man hinh 1
        composable("email_step") {
            EmailScreen(
                onNext = { emailInput ->
                    state.email = emailInput
                    navController.navigate("otp_step")
                }
            )
        }

        //man hinh 2
        composable("otp_step") {
            OtpScreen(
                onBack = { navController.popBackStack() },
                onNext = { otpInput ->
                    state.otpCode = otpInput
                    navController.navigate("pass_step")
                }
            )
        }

        //man hinh 3
        composable("pass_step") {
            ResetPasswordScreen(
                onBack = { navController.popBackStack() },
                onNext = { passInput ->
                    state.newPassword = passInput
                    navController.navigate("confirm_step")
                }
            )
        }

        //man hinh 4
        composable("confirm_step") {
            ConfirmScreen(
                email = state.email,
                otp = state.otpCode,
                pass = state.newPassword,
                onBack = { navController.popBackStack() },
                onSubmit = {
                    onFinished()
                }
            )
        }
    }
}