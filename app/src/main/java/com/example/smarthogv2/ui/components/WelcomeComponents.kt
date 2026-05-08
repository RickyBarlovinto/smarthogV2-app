package com.example.smarthogv2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.ui.theme.PureBlack

@Composable
fun WelcomeHeader(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PureBlack,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = subtitle,
            fontSize = 16.sp,
            color = PureBlack,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}
