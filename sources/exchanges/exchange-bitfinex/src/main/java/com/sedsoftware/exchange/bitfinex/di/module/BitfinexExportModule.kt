package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairLoader
import com.sedsoftware.exchange.bitfinex.BitfinexPairManager
import dagger.Binds
import dagger.Module

@Module
abstract class BitfinexExportModule {

    @Binds
    @ForExchange(BITFINEX)
    abstract fun bindBinancePairLoader(implementation: BitfinexPairLoader): CurrencyPairLoader

    @Binds
    @ForExchange(BITFINEX)
    abstract fun bindBinancePairManager(implementation: BitfinexPairManager): CurrencyPairManager
}
