package com.example.smarthogv2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.R
import com.example.smarthogv2.ui.components.SmartHogLogo
import com.example.smarthogv2.ui.components.WelcomeHeader
import com.example.smarthogv2.ui.theme.ForestGreen
import com.example.smarthogv2.ui.theme.PureWhite

@Composable
fun WelcomeScreen(onNavigateToLogin: () -> Unit) {
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
                .background(PureWhite.copy(alpha = 0.6f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pig_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                WelcomeHeader(
                    title = "Smart Farming,\nEfficient Living",
                    subtitle = "Remote monitoring and automated feeding system designed for modern agriculture."
                )
            }
            
            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)),
                shape = RoundedCornerShape(32.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("GET STARTED", fontWeight = FontWeight.Black, color = PureWhite, letterSpacing = 2.sp, fontSize = 18.sp)
            }
        }
    }
}
