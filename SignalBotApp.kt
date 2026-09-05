package com.megcup.signalbot.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.megcup.signalbot.domain.model.Signal

@Composable
fun SignalBotApp(vm: SignalBotViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF090A0F)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("SIGNALBOT V4", style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold)
                Text("AI-style M1 / M5 / M15 market scanner", color = Color.LightGray)
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("EUR/USD", "GBP/USD", "USD/JPY").forEach { asset ->
                        FilterChip(
                            selected = state.asset == asset,
                            onClick = { vm.setAsset(asset) },
                            label = { Text(asset) }
                        )
                    }
                }
            }

            item {
                Card(shape = RoundedCornerShape(20.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            state.signal?.signal?.name ?: "NO SIGNAL",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Confidence: ${state.signal?.confidence ?: 0}%",
                            color = Color.LightGray
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { vm.scan() },
                            enabled = !state.scanning,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (state.scanning) "ANALYZING…" else "START MARKET SCAN")
                        }
                    }
                }
            }

            item {
                Text("Multi-timeframe confirmation",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold)
            }

            items(state.signal?.analyses.orEmpty()) { a ->
                Card(shape = RoundedCornerShape(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(a.timeframe.name, fontWeight = FontWeight.Bold)
                            Text(a.trend, color = Color.LightGray)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(a.signal.name, fontWeight = FontWeight.Bold)
                            Text("${a.confidence}%")
                        }
                    }
                }
            }

            item {
                Card(shape = RoundedCornerShape(16.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Risk controls", fontWeight = FontWeight.Bold)
                        Text("1% default risk • 5% daily loss stop • 3-loss stop")
                        Text("Paper mode is ON. Real-money execution requires an officially documented API.")
                    }
                }
            }

            item {
                Text(state.message, color = Color.Gray)
            }
        }
    }
}
