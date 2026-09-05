package com.megcup.signalbot.data.execution

import com.megcup.signalbot.domain.model.Signal

data class TradeRequest(
    val asset: String,
    val direction: Signal,
    val amount: Double,
    val expirySeconds: Int
)

data class TradeResult(
    val accepted: Boolean,
    val externalId: String? = null,
    val message: String
)

interface TradeExecutor {
    suspend fun placeTrade(request: TradeRequest): TradeResult
    suspend fun cancelPending(): Boolean
}
