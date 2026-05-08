package com.example.smarthogv2.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.ui.theme.*

@Composable
fun DashboardStatusCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = LightMint),
        border = androidx.compose.foundation.BorderStroke(1.dp, MintGreen)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.labelMedium, color = PureBlack)
            }
            Spacer(Modifier.height(8.dp))
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = color)
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
fun SectionTitle(title: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
        Icon(icon, contentDescription = null, tint = ForestGreen, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(8.dp))
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PureBlack)
    }
}

@Composable

fun ControlBtn(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    containerColor: Color,
    contentColor: Color = PureWhite // Changed default to White for better contrast
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = Color.Gray.copy(alpha = 0.3f)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp) // Added shadow
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)
    }
}

@Composable
fun ActiveIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse), label = "alpha"
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(ForestGreen.copy(alpha = alpha)))
        Spacer(Modifier.width(8.dp))
        Text("ACTIVE", style = MaterialTheme.typography.labelSmall, color = PureBlack)
    }
}

@Composable
fun AlertBox(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
        border = androidx.compose.foundation.BorderStroke(1.dp, AlertRed)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Warning, contentDescription = null, tint = AlertRed)
            Spacer(Modifier.width(12.dp))
            Text(message, color = PureBlack, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ScheduleItem(time: String, label: String, isActive: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = if (isActive) MintGreen.copy(alpha = 0.4f) else LightMint),
        border = androidx.compose.foundation.BorderStroke(1.dp, if (isActive) ForestGreen else MintGreen)
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(time, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PureBlack)
                Text(label, fontSize = 12.sp, color = PureBlack)
            }
            Switch(
                checked = isActive,
                onCheckedChange = {},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = PureWhite,
                    checkedTrackColor = ForestGreen,
                    uncheckedThumbColor = ForestGreen,
                    uncheckedTrackColor = LightMint,
                    uncheckedBorderColor = ForestGreen
                )
            )
        }
    }
}

@Composable
fun VoiceDialog(onDismiss: () -> Unit, onCommandRecognized: (String) -> Unit = {}) {
    var isListening by remember { mutableStateOf(true) }
    var recognizedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        isListening = false
        recognizedText = "Open Feeder"
        kotlinx.coroutines.delay(1000)
        onCommandRecognized(recognizedText)
        onDismiss()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel", color = PureBlack) } },
        containerColor = PureWhite,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text("Voice Assistant", color = PureBlack, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                
                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val scale by if (isListening) {
                    infiniteTransition.animateFloat(
                        initialValue = 1f, targetValue = 1.2f,
                        animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse), label = "scale"
                    )
                } else {
                    remember { mutableStateOf(1f) }
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .clip(CircleShape)
                        .background(if (isListening) ForestGreen else DarkGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isListening) Icons.Default.Mic else Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = PureWhite
                    )
                }
            }
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    if (isListening) "Listening for commands..." else "Recognized: \"$recognizedText\"",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = PureBlack
                )
                Spacer(Modifier.height(8.dp))
                Text("Try: \"Open feeder\" or \"Start feeding\"", style = MaterialTheme.typography.labelSmall, color = DarkGreen)
            }
        }
    )
}
