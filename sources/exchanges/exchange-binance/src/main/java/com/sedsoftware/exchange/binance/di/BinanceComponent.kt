package com.sedsoftware.exchange.binance.di

import com.sedsoftware.core.di.BinanceProvider
import com.sedsoftware.core.di.CoinMarketCapProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.exchange.binance.di.module.BinanceDatabaseModule
import com.sedsoftware.exchange.binance.di.module.BinanceExportModule
import com.sedsoftware.exchange.binance.di.module.BinanceNetworkModule
import com.sedsoftware.exchange.binance.di.module.BinanceRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        CoinMarketCapProvider::class
    ],
    modules = [
        BinanceDatabaseModule::class,
        BinanceNetworkModule::class,
        BinanceRepositoryModule::class,
        BinanceExportModule::class
    ]
)
@Singleton
interface BinanceComponent : BinanceProvider {
    class Initializer private constructor() {
        companion object {

            fun init(
                deviceToolsProvider: DeviceToolsProvider,
                coinMarketCapProvider: CoinMarketCapProvider
            ): BinanceProvider {
                return DaggerBinanceComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .coinMarketCapProvider(coinMarketCapProvider)
                    .build()
            }
        }
    }
}
