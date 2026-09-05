package com.megcup.signalbot.domain.model

enum class Timeframe { M1, M5, M15 }
enum class Signal { CALL, PUT, NO_TRADE }

data class Candle(
    val timeMs: Long,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double
)

data class IndicatorSnapshot(
    val ema9: Double,
    val ema21: Double,
    val rsi14: Double,
    val macd: Double,
    val bbMiddle: Double,
    val bbUpper: Double,
    val bbLower: Double
)

data class TimeframeAnalysis(
    val timeframe: Timeframe,
    val trend: String,
    val signal: Signal,
    val confidence: Int,
    val indicators: IndicatorSnapshot
)

data class MultiTimeframeSignal(
    val asset: String,
    val primary: Timeframe,
    val signal: Signal,
    val confidence: Int,
    val analyses: List<TimeframeAnalysis>,
    val generatedAtMs: Long
)

data class RiskSettings(
    val accountBalance: Double = 100.0,
    val riskPercent: Double = 1.0,
    val maxDailyLossPercent: Double = 5.0,
    val maxConsecutiveLosses: Int = 3,
    val paperMode: Boolean = true
)
