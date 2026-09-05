package com.megcup.signalbot.data.market

import com.megcup.signalbot.domain.model.Candle
import com.megcup.signalbot.domain.model.Timeframe

interface MarketDataProvider {
    suspend fun candles(asset: String, timeframe: Timeframe, limit: Int = 200): List<Candle>
}
