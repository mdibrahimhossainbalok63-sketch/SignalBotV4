package com.megcup.signalbot.data.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.megcup.signalbot.domain.model.RiskSettings

private val Context.dataStore by preferencesDataStore("signalbot_settings")

class SettingsRepository(private val context: Context) {
    private val balance = doublePreferencesKey("balance")
    private val risk = doublePreferencesKey("risk")
    private val maxLoss = doublePreferencesKey("max_daily_loss")
    private val maxConsecutive = androidx.datastore.preferences.core.intPreferencesKey("max_consecutive")
    private val paper = booleanPreferencesKey("paper_mode")

    val settings: Flow<RiskSettings> = context.dataStore.data.map { p ->
        RiskSettings(
            accountBalance = p[balance] ?: 100.0,
            riskPercent = p[risk] ?: 1.0,
            maxDailyLossPercent = p[maxLoss] ?: 5.0,
            maxConsecutiveLosses = p[maxConsecutive] ?: 3,
            paperMode = p[paper] ?: true
        )
    }

    suspend fun save(s: RiskSettings) {
        context.dataStore.edit {
            it[balance] = s.accountBalance
            it[risk] = s.riskPercent
            it[maxLoss] = s.maxDailyLossPercent
            it[maxConsecutive] = s.maxConsecutiveLosses
            it[paper] = s.paperMode
        }
    }
}
