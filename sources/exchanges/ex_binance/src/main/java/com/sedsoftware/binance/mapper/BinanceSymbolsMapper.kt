package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.binance.entity.BinanceCurrency
import com.sedsoftware.binance.network.model.SymbolInfoModel
import javax.inject.Inject

class BinanceSymbolsMapper @Inject constructor() {

    fun mapFromCloudToDb(from: SymbolInfoModel, timestamp: Long): BinanceSymbolDbModel =
        BinanceSymbolDbModel(
            symbol = from.symbol,
            syncDate = timestamp,
            status = from.status,
            baseAsset = from.baseAsset,
            baseAssetPrecision = from.baseAssetPrecision,
            quoteAsset = from.quoteAsset,
            quotePrecision = from.quotePrecision,
            orderTypes = from.orderTypes,
            icebergAllowed = from.icebergAllowed,
            filters = from.filters
        )

    fun mapBaseSymbolsFromDb(from: List<BinanceSymbolDbModel>): List<BinanceCurrency> =
        from.map { enumValueOf<BinanceCurrency>(it.baseAsset) }

    fun mapMarketSymbolsFromDb(from: List<BinanceSymbolDbModel>): List<BinanceCurrency> =
        from.map { enumValueOf<BinanceCurrency>(it.quoteAsset) }
}
