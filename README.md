# SignalBot V4 — Android Project Structure

V4 is a native Android/Jetpack Compose foundation for a professional trading-signal app.

## Included

- Native Android app structure
- Jetpack Compose UI
- M1 / M5 / M15 multi-timeframe engine
- EMA 9 / EMA 21
- RSI 14
- MACD-style EMA spread
- Bollinger Bands
- CALL / PUT / NO TRADE decision
- Multi-timeframe confirmation
- Confidence score
- Risk engine
- Daily loss limit
- Consecutive-loss limit
- Paper-trade executor
- Market-data provider interface
- Separate official-API execution adapter for future use
- DataStore settings repository

## Important

The Quotex adapter is deliberately disabled until there is a documented, officially authorized Quotex developer/trading API.

This project does NOT:
- collect Quotex passwords
- collect OTPs
- extract cookies or SSID/session tokens
- automate browser clicks
- bypass authentication
- pretend an unofficial reverse-engineered library is an official API

## Build

Open this folder in Android Studio and let Gradle sync.

Then run:
`./gradlew assembleDebug`

The resulting debug APK will be under:
`app/build/outputs/apk/debug/`

## Next production modules

For a production release, add:
- real market-data provider (official/licensed)
- authenticated official broker API adapter
- encrypted secret storage / Android Keystore
- WebSocket streaming
- persistent trade journal
- notification service
- emergency stop
- duplicate-trade protection
- connectivity watchdog
- server-side risk controls
- unit/integration/backtest test suite
- Play Integrity / release signing
