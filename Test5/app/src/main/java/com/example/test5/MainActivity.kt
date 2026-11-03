package com.example.test5 // <--- Nhớ giữ lại package của bạn ở dòng đầu tiên này nếu tên package của bạn khác

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// --- 1. VIEWMODEL LƯU TRỮ DỮ LIỆU QUA CÁC MÀN HÌNH ---
class ForgotPasswordViewModel : ViewModel() {
    var email by mutableStateOf("")
    var otp by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
}

// --- 2. ĐỊNH NGHĨA CÁC ĐƯỜNG DẪN MÀN HÌNH ---
sealed class Screen(val route: String) {
    object Forgot : Screen("forgot_screen")
    object Verify : Screen("verify_screen")
    object NewPassword : Screen("new_password_screen")
    object Confirm : Screen("confirm_screen")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val viewModel: ForgotPasswordViewModel = viewModel()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.Forgot.route) {
                        composable(Screen.Forgot.route) {
                            ForgotScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(Screen.Verify.route) {
                            VerifyScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(Screen.NewPassword.route) {
                            NewPasswordScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(Screen.Confirm.route) {
                            ConfirmScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

// ================= GIAO DIỆN CHI TIẾT 4 MÀN HÌNH =================

// MÀN HÌNH 1: Nhập Email
@Composable
fun ForgotScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SmartTasks", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00796B))
        Spacer(modifier = Modifier.height(32.dp))
        Text("Forget Password?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enter your Email, we will send you a verification code.", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Your Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.Verify.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Next", fontSize = 16.sp, color = Color.White)
        }
    }
}

// MÀN HÌNH 2: Nhập OTP
@Composable
fun VerifyScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Verify Code", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enter the code we just sent you on your registered Email", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.otp,
            onValueChange = { viewModel.otp = it },
            label = { Text("Enter OTP Code") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.NewPassword.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Next", fontSize = 16.sp, color = Color.White)
        }
    }
}

// MÀN HÌNH 3: Tạo Mật khẩu mới
@Composable
fun NewPasswordScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create new password", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Your new password must be different from previously used password", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.Confirm.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Next", fontSize = 16.sp, color = Color.White)
        }
    }
}

// MÀN HÌNH 4: Xác nhận (Confirm hiển thị lại toàn bộ dữ liệu từ các màn trước)
@Composable
fun ConfirmScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Confirm", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("We are here to help you!", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {},
            readOnly = true,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.otp,
            onValueChange = {},
            readOnly = true,
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {},
            readOnly = true,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Hoàn tất và quay lại màn hình đầu tiên
                navController.popBackStack(Screen.Forgot.route, inclusive = false)
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Submit", fontSize = 16.sp, color = Color.White)
        }
    }
}