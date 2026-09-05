package com.megcup.signalbot.domain.risk

import com.megcup.signalbot.domain.model.RiskSettings

data class RiskDecision(
    val allowed: Boolean,
    val amount: Double,
    val reason: String
)

class RiskEngine {
    fun decide(settings: RiskSettings, dailyPnl: Double, consecutiveLosses: Int): RiskDecision {
        if (dailyPnl <= -(settings.accountBalance * settings.maxDailyLossPercent / 100.0)) {
            return RiskDecision(false, 0.0, "Daily loss limit reached")
        }
        if (consecutiveLosses >= settings.maxConsecutiveLosses) {
            return RiskDecision(false, 0.0, "Consecutive-loss limit reached")
        }
        val amount = settings.accountBalance * settings.riskPercent / 100.0
        return RiskDecision(true, amount, "Risk limits passed")
    }
}
