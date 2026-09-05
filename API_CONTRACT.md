# Broker API Contract

The app isolates broker execution behind `TradeExecutor`.

Expected official API capabilities:

1. Authentication
2. Account balance
3. Asset availability
4. Quote / market data
5. Trade placement
6. Trade status / result
7. Error codes
8. Rate limits
9. Sandbox/demo environment if available

When official documentation is obtained, implement these endpoints inside:
`data/execution/QuotexOfficialApiExecutor.kt`

Never put passwords, OTPs, cookies, SSIDs, or API secrets into source control.
