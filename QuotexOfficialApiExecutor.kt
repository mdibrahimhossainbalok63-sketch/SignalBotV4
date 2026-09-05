package com.megcup.signalbot.data.execution

/**
 * Reserved adapter for a documented, officially authorized Quotex API.
 *
 * This class intentionally does NOT automate the Quotex website, extract
 * cookies/SSID, bypass authentication, or simulate browser clicks.
 *
 * When Quotex provides an official developer API, implement:
 * - official authentication
 * - account/balance endpoint
 * - supported asset/quote endpoint
 * - trade/order endpoint
 * - order result/status endpoint
 * - official rate limits and error handling
 */
class QuotexOfficialApiExecutor : TradeExecutor {
    override suspend fun placeTrade(request: TradeRequest): TradeResult =
        TradeResult(
            accepted = false,
            message = "Official Quotex trading API is not configured."
        )

    override suspend fun cancelPending(): Boolean = false
}
