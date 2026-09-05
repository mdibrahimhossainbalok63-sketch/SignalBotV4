package com.megcup.signalbot.domain.engine

import com.megcup.signalbot.domain.model.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class SignalEngine {

    fun analyze(asset: String, candlesByTf: Map<Timeframe, List<Candle>>): MultiTimeframeSignal {
        val analyses = Timeframe.entries.map { tf ->
            analyzeTimeframe(tf, candlesByTf[tf].orEmpty())
        }

        val bullish = analyses.count { it.signal == Signal.CALL }
        val bearish = analyses.count { it.signal == Signal.PUT }

        val signal = when {
            bullish >= 2 && bullish > bearish -> Signal.CALL
            bearish >= 2 && bearish > bullish -> Signal.PUT
            else -> Signal.NO_TRADE
        }

        val agreement = max(bullish, bearish)
        val confidence = min(95, 50 + agreement * 15 + analyses.map { it.confidence }.average().toInt() / 4)

        return MultiTimeframeSignal(
            asset = asset,
            primary = Timeframe.M5,
            signal = signal,
            confidence = confidence,
            analyses = analyses,
            generatedAtMs = System.currentTimeMillis()
        )
    }

    private fun analyzeTimeframe(tf: Timeframe, candles: List<Candle>): TimeframeAnalysis {
        if (candles.size < 25) {
            return TimeframeAnalysis(
                tf, "INSUFFICIENT DATA", Signal.NO_TRADE, 0,
                IndicatorSnapshot(0.0, 0.0, 50.0, 0.0, 0.0, 0.0, 0.0)
            )
        }

        val closes = candles.map { it.close }
        val ema9 = ema(closes, 9)
        val ema21 = ema(closes, 21)
        val rsi = rsi(closes, 14)
        val macd = ema9 - ema21
        val mean = closes.takeLast(20).average()
        val sd = sqrt(closes.takeLast(20).map { (it - mean) * (it - mean) }.average())
        val upper = mean + 2 * sd
        val lower = mean - 2 * sd

        val bullishScore =
            (if (ema9 > ema21) 1 else 0) +
            (if (rsi in 52.0..72.0) 1 else 0) +
            (if (macd > 0) 1 else 0) +
            (if (closes.last() > mean) 1 else 0)

        val bearishScore =
            (if (ema9 < ema21) 1 else 0) +
            (if (rsi in 28.0..48.0) 1 else 0) +
            (if (macd < 0) 1 else 0) +
            (if (closes.last() < mean) 1 else 0)

        val signal = when {
            bullishScore >= 3 && bullishScore > bearishScore -> Signal.CALL
            bearishScore >= 3 && bearishScore > bullishScore -> Signal.PUT
            else -> Signal.NO_TRADE
        }

        val confidence = when (max(bullishScore, bearishScore)) {
            4 -> 90
            3 -> 75
            2 -> 60
            else -> 45
        }

        return TimeframeAnalysis(
            timeframe = tf,
            trend = when {
                ema9 > ema21 -> "BULLISH"
                ema9 < ema21 -> "BEARISH"
                else -> "SIDEWAYS"
            },
            signal = signal,
            confidence = confidence,
            indicators = IndicatorSnapshot(ema9, ema21, rsi, macd, mean, upper, lower)
        )
    }

    private fun ema(values: List<Double>, period: Int): Double {
        val k = 2.0 / (period + 1)
        var result = values.take(period).average()
        for (v in values.drop(period)) result = v * k + result * (1 - k)
        return result
    }

    private fun rsi(values: List<Double>, period: Int): Double {
        var gain = 0.0
        var loss = 0.0
        for (i in 1..period) {
            val d = values[i] - values[i - 1]
            if (d >= 0) gain += d else loss -= d
        }
        var avgGain = gain / period
        var avgLoss = loss / period
        for (i in (period + 1) until values.size) {
            val d = values[i] - values[i - 1]
            avgGain = (avgGain * (period - 1) + max(d, 0.0)) / period
            avgLoss = (avgLoss * (period - 1) + max(-d, 0.0)) / period
        }
        if (avgLoss == 0.0) return 100.0
        return 100.0 - (100.0 / (1.0 + avgGain / avgLoss))
    }
}
