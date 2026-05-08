package com.example.smarthogv2.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.R
import com.example.smarthogv2.ui.theme.*

@Composable
fun SmartHogLogo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pig_logo),
            contentDescription = "Smart Hog Logo",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "SMART HOG",
            color = PureBlack,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun LogoLoadingIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(PureWhite)) {
        Box(modifier = Modifier.size(180.dp), contentAlignment = Alignment.Center) {
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = ForestGreen,
                    startAngle = rotation,
                    sweepAngle = 280f,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.pig_logo),
                contentDescription = "Loading",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHogTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null,
    visualTransformation: androidx.compose.ui.text.input.VisualTransformation = androidx.compose.ui.text.input.VisualTransformation.None,
    keyboardOptions: androidx.compose.foundation.text.KeyboardOptions = androidx.compose.foundation.text.KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = PureBlack, fontWeight = FontWeight.Medium) },
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        supportingText = supportingText?.let { { Text(it, color = if (isError) Color.Red else PureBlack) } },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ForestGreen,
            unfocusedBorderColor = DarkGreen,
            focusedTextColor = PureBlack,
            unfocusedTextColor = PureBlack,
            cursorColor = PureBlack,
            errorBorderColor = Color.Red,
            focusedLabelColor = PureBlack,
            unfocusedLabelColor = PureBlack,
            errorLabelColor = Color.Red,
            focusedPlaceholderColor = PureBlack,
            unfocusedPlaceholderColor = PureBlack
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        singleLine = true
    )
}
