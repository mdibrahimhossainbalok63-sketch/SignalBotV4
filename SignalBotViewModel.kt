package com.megcup.signalbot.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megcup.signalbot.data.market.MockMarketDataProvider
import com.megcup.signalbot.domain.engine.SignalEngine
import com.megcup.signalbot.domain.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BotUiState(
    val asset: String = "EUR/USD",
    val signal: MultiTimeframeSignal? = null,
    val scanning: Boolean = false,
    val message: String = "Ready",
    val paperMode: Boolean = true
)

class SignalBotViewModel : ViewModel() {
    private val provider = MockMarketDataProvider()
    private val engine = SignalEngine()

    private val _state = MutableStateFlow(BotUiState())
    val state = _state.asStateFlow()

    fun setAsset(asset: String) {
        _state.value = _state.value.copy(asset = asset)
    }

    fun scan() {
        if (_state.value.scanning) return
        viewModelScope.launch {
            _state.value = _state.value.copy(scanning = true, message = "Market scan in progress…")
            val asset = _state.value.asset
            val map = Timeframe.entries.associateWith { provider.candles(asset, it) }
            val result = engine.analyze(asset, map)
            _state.value = _state.value.copy(
                scanning = false,
                signal = result,
                message = "Scan complete"
            )
        }
    }
}
