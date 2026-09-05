# Architecture

UI
  ↓
SignalBotViewModel
  ↓
Domain
  ├── SignalEngine
  └── RiskEngine
  ↓
Data interfaces
  ├── MarketDataProvider
  └── TradeExecutor
       ├── PaperTradeExecutor
       └── QuotexOfficialApiExecutor (disabled placeholder)

This separation allows the broker integration to be replaced without rewriting the signal engine or UI.
