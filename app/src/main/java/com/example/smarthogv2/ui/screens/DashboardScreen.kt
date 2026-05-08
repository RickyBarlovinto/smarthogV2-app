package com.example.smarthogv2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthogv2.R
import com.example.smarthogv2.ui.components.*
import com.example.smarthogv2.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var feederOpen by remember { mutableStateOf(false) }
    var feedingActive by remember { mutableStateOf(false) }
    var feedLevel by remember { mutableFloatStateOf(0.75f) }
    var currentTime by remember { mutableStateOf(SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())) }
    var showVoiceDialog by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var scheduleList = remember { mutableStateListOf(
        "06:00 AM" to "Daily Feeding"
    ) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            kotlinx.coroutines.delay(1000)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.pig_logo),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp).clip(CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("SMART-HOG", fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp, color = PureBlack)
                            Text("Remote Controller", style = MaterialTheme.typography.labelSmall, color = ForestGreen)
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showVoiceDialog = true },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MintGreen.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            Icons.Default.Mic, 
                            contentDescription = "Voice Command", 
                            tint = DarkGreen,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PureWhite,
                    titleContentColor = PureBlack
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().background(PureWhite)) {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { Spacer(Modifier.height(8.dp)) }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("SYSTEM STATUS", style = MaterialTheme.typography.labelSmall, color = ForestGreen, fontWeight = FontWeight.Bold)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(ForestGreen))
                                    Spacer(Modifier.width(4.dp))
                                    Text("ONLINE", style = MaterialTheme.typography.labelSmall, color = ForestGreen)
                                }
                            }
                            Text("MAIN FEEDER UNIT #04", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PureBlack)
                            
                            Spacer(Modifier.height(16.dp))
                            
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Card(
                                    modifier = Modifier.size(80.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = MintGreen.copy(alpha = 0.3f))
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            if (feederOpen) Icons.Default.SensorDoor else Icons.Default.DoorBack,
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp),
                                            tint = ForestGreen
                                        )
                                    }
                                }
                                Spacer(Modifier.width(16.dp))
                                Column {
                                    Text("CURRENT STATE", style = MaterialTheme.typography.labelSmall, color = DarkGreen)
                                    Text(if (feederOpen) "OPEN" else "CLOSED", fontSize = 32.sp, fontWeight = FontWeight.Black, color = PureBlack)
                                }
                                Spacer(Modifier.weight(1f))
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("UPTIME", style = MaterialTheme.typography.labelSmall, color = DarkGreen)
                                    Text("12h 32m", fontWeight = FontWeight.Bold, color = PureBlack)
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("FEED LEVEL", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = PureBlack)
                                Text("${(feedLevel * 100).toInt()}%", fontWeight = FontWeight.Bold, color = if (feedLevel < 0.2f) AlertRed else ForestGreen)
                            }
                            Spacer(Modifier.height(8.dp))
                            LinearProgressIndicator(
                                progress = feedLevel,
                                modifier = Modifier.fillMaxWidth().height(12.dp).clip(CircleShape),
                                color = if (feedLevel < 0.2f) AlertRed else ForestGreen,
                                trackColor = MintGreen.copy(alpha = 0.2f)
                            )
                            
                            if (feedLevel < 0.2f) {
                                Spacer(Modifier.height(16.dp))
                                AlertBox(message = "Low Feed! Refill within 4h.")
                            }
                        }
                    }
                }

                item { SectionTitle(title = "Manual Controls", icon = Icons.Default.SettingsRemote) }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ControlBtn(
                                modifier = Modifier.weight(1f),
                                text = "START FEEDING",
                                icon = Icons.Default.PlayArrow,
                                onClick = { feedingActive = true; feederOpen = true },
                                containerColor = ForestGreen,
                                contentColor = PureWhite,
                                enabled = !feedingActive
                            )
                            ControlBtn(
                                modifier = Modifier.weight(1f),
                                text = "STOP FEEDING",
                                icon = Icons.Default.Stop,
                                onClick = { feedingActive = false },
                                containerColor = AlertRed,
                                contentColor = PureWhite,
                                enabled = feedingActive
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ControlBtn(
                                modifier = Modifier.weight(1f),
                                text = "OPEN FEEDER",
                                icon = Icons.Default.KeyboardArrowUp,
                                onClick = { feederOpen = true },
                                enabled = !feederOpen,
                                containerColor = MintGreen
                            )
                            ControlBtn(
                                modifier = Modifier.weight(1f),
                                text = "CLOSE FEEDER",
                                icon = Icons.Default.KeyboardArrowDown,
                                onClick = { feederOpen = false },
                                enabled = feederOpen,
                                containerColor = MintGreen
                            )
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = LightMint),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ForestGreen)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.PrecisionManufacturing, contentDescription = null, tint = PureBlack, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("Servo Angle Control", style = MaterialTheme.typography.titleSmall, color = PureBlack)
                            }
                            var sliderValue by remember { mutableFloatStateOf(0f) }
                            Slider(
                                value = sliderValue,
                                onValueChange = { sliderValue = it },
                                valueRange = 0f..180f,
                                colors = SliderDefaults.colors(
                                    thumbColor = DarkGreen,
                                    activeTrackColor = ForestGreen,
                                    inactiveTrackColor = MintGreen
                                )
                            )
                            Text("${sliderValue.toInt()}°", modifier = Modifier.align(Alignment.End), fontWeight = FontWeight.Bold, color = PureBlack)
                        }
                    }
                }

                item {
                    Column {
                        SectionTitle(title = "Live Monitoring", icon = Icons.Default.Videocam)
                        Card(
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pigs),
                                contentDescription = "Live Feed",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionTitle(title = "Feeding Schedule", icon = Icons.Default.Schedule)
                        Text(currentTime, fontWeight = FontWeight.Bold, color = PureBlack)
                    }
                }

                items(scheduleList.size) { index ->
                    val (time, label) = scheduleList[index]
                    ScheduleItem(time = time, label = label, isActive = true)
                }
                
                item {
                    Button(
                        onClick = { showTimePicker = true },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MintGreen),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = DarkGreen)
                        Spacer(Modifier.width(8.dp))
                        Text("ADD FEEDING SCHEDULE", color = DarkGreen, fontWeight = FontWeight.Bold)
                    }
                }

                item { Spacer(Modifier.height(32.dp)) }
            }
        }
    }

    if (showVoiceDialog) {
        VoiceDialog(
            onDismiss = { showVoiceDialog = false },
            onCommandRecognized = { command ->
                when (command.lowercase()) {
                    "open feeder" -> feederOpen = true
                    "close feeder" -> feederOpen = false
                    "start feeding" -> {
                        feedingActive = true
                        feederOpen = true
                    }
                    "stop feeding" -> feedingActive = false
                }
            }
        )
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState()
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", timePickerState.hour, timePickerState.minute)
                    scheduleList.add(formattedTime to "Scheduled Feeding")
                    showTimePicker = false
                }) { Text("Add") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}
