package com.example.smarthogv2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.R
import com.example.smarthogv2.ui.components.LoginHeader
import com.example.smarthogv2.ui.components.LogoLoadingIndicator
import com.example.smarthogv2.ui.components.RememberMeRow
import com.example.smarthogv2.ui.components.SmartHogLogo
import com.example.smarthogv2.ui.components.SmartHogTextField
import com.example.smarthogv2.ui.theme.ForestGreen
import com.example.smarthogv2.ui.theme.PureBlack
import com.example.smarthogv2.ui.theme.PureWhite
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    if (isLoading) {
        LogoLoadingIndicator()
        LaunchedEffect(Unit) {
            delay(2000) // Simulate loading
            isLoading = false
            onLoginSuccess()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.pigs),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // Translucent Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PureWhite.copy(alpha = 0.7f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo Section
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.pig_logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        "SMART HOG",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF6B2B45), // Dark purple/maroon from the logo
                        letterSpacing = 2.sp
                    )
                    Text(
                        "AUTOMATED FEEDING & MONITORING",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE65100), // Orange from image
                        letterSpacing = 1.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(48.dp))
                
                SmartHogTextField(
                    value = email,
                    onValueChange = { 
                        email = it
                        emailError = null 
                    },
                    label = "Username or Email",
                    isError = emailError != null,
                    supportingText = emailError
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                SmartHogTextField(
                    value = password,
                    onValueChange = { 
                        password = it
                        passwordError = null
                    },
                    label = "Password",
                    isError = passwordError != null,
                    supportingText = passwordError,
                    visualTransformation = PasswordVisualTransformation()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                RememberMeRow(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    onForgotPasswordClick = { /* Handle click */ }
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = {
                        if (email.isBlank()) {
                            emailError = "Please enter email"
                        } else if (password.length < 6) {
                            passwordError = "Password too short"
                        } else {
                            isLoading = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)), // Orange Login button
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("LOGIN", style = MaterialTheme.typography.labelLarge, color = PureWhite, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
