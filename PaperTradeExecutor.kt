package com.megcup.signalbot.data.execution

class PaperTradeExecutor : TradeExecutor {
    override suspend fun placeTrade(request: TradeRequest): TradeResult =
        TradeResult(true, "PAPER-${System.currentTimeMillis()}", "Paper trade accepted")

    override suspend fun cancelPending(): Boolean = true
}
