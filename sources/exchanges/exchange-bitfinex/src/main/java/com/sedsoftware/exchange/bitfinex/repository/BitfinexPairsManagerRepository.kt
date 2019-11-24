package com.sedsoftware.exchange.bitfinex.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.bitfinex.database.BitfinexDatabase
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexSymbolsDao
import javax.inject.Inject

class BitfinexPairsManagerRepository @Inject constructor(
    private val db: BitfinexDatabase
) {

    private val symbolsDao: BitfinexSymbolsDao by lazy {
        db.getBitfinexSymbolsDao()
    }

    suspend fun getBaseCurrencies(): List<Currency> =
        symbolsDao
            .getBaseCurrencies()
            .map {
                object : Currency {
                    override val name: String = it.baseAsset
                    override val label: String = it.baseAssetName
                }
            }

    suspend fun getMarketCurrencies(base: Currency): List<Currency> =
        symbolsDao
            .getCurrenciesForBase(base.name)
            .map {
                object : Currency {
                    override val name: String = it.quoteAsset
                    override val label: String = it.quoteAssetName
                }
            }
}