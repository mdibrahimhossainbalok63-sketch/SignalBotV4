package com.megcup.signalbot.data.market

import com.megcup.signalbot.domain.model.Candle
import com.megcup.signalbot.domain.model.Timeframe
import kotlin.math.sin
import kotlin.random.Random

class MockMarketDataProvider : MarketDataProvider {
    override suspend fun candles(asset: String, timeframe: Timeframe, limit: Int): List<Candle> {
        var price = 1.1000
        val step = when (timeframe) {
            Timeframe.M1 -> 60_000L
            Timeframe.M5 -> 300_000L
            Timeframe.M15 -> 900_000L
        }
        val now = System.currentTimeMillis()
        return (0 until limit).map { i ->
            val drift = sin(i / 12.0) * 0.0005 + Random.nextDouble(-0.00025, 0.00025)
            val open = price
            price = (price + drift).coerceAtLeast(0.0001)
            val close = price
            val high = maxOf(open, close) + Random.nextDouble(0.0, 0.00015)
            val low = minOf(open, close) - Random.nextDouble(0.0, 0.00015)
            Candle(now - (limit - i) * step, open, high, low, close)
        }
    }
}
