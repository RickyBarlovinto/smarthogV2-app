package com.example.smarthogv2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.ui.theme.DarkGreen
import com.example.smarthogv2.ui.theme.ForestGreen
import com.example.smarthogv2.ui.theme.PureBlack
import com.example.smarthogv2.ui.theme.PureWhite

@Composable
fun LoginHeader(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = PureBlack
    )
}

@Composable
fun RememberMeRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = PureWhite,
                    checkedColor = ForestGreen,
                    uncheckedColor = DarkGreen
                )
            )
            Text("Remember me", color = PureBlack, fontSize = 14.sp)
        }
        TextButton(onClick = onForgotPasswordClick) {
            Text("Forgot Password?", color = ForestGreen, fontWeight = FontWeight.Bold)
        }
    }
}
