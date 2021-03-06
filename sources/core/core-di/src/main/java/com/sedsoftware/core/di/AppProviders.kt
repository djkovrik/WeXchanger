package com.sedsoftware.core.di

import android.view.Display
import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.domain.tools.Logger
import com.sedsoftware.core.domain.tools.NetworkHandler
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.domain.tools.Settings
import com.sedsoftware.core.domain.tools.Signer
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient

// App
interface AppProvider :
    DeviceToolsProvider,
    ExchangeManagerProvider,
    CoinMarketCapProvider

// Global providers
interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideLogger(): Logger
    fun provideNetworkHandler(): NetworkHandler
    fun provideSettings(): Settings
    fun provideSigner(): Signer
    fun provideCiceroneManager(): CiceroneManager
    fun provideErrorHandler(): ErrorHandler
    fun provideResourceManager(): ResourceManager
    fun provideMoshi(): Moshi
    fun provideOkHttpClient(): OkHttpClient
    fun provideDefaultDisplay(): Display
}

interface ExchangeManagerProvider {
    fun provideExchangePairLoaders(): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
    fun provideExchangePairManagers(): Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
    fun provideExchangeTickerManagers(): Map<Exchange, @JvmSuppressWildcards TickerManager>
    fun provideIconsProvider(): AssetsProvider
}

interface BinanceProvider {
    @ForExchange(BINANCE) fun provideBinancePairLoader(): CurrencyPairLoader
    @ForExchange(BINANCE) fun provideBinancePairManager(): CurrencyPairManager
    @ForExchange(BINANCE) fun provideBinancePairTicksManager(): TickerManager
}

interface BitfinexProvider {
    @ForExchange(BITFINEX) fun provideBitfinexPairLoader(): CurrencyPairLoader
    @ForExchange(BITFINEX) fun provideBitfinexPairManager(): CurrencyPairManager
    @ForExchange(BITFINEX) fun provideBitfinexPairTicksManager(): TickerManager
}

interface CoinMarketCapProvider {
    fun provideCurrencyProvider(): CurrencyManager
    fun provideCurrencyMapLoader(): CurrencyMapLoader
}

// Local providers
interface ActivityToolsProvider : AppProvider {
    fun provideFlowSwitcher(): FlowSwitcher
    fun provideStartingFlowCoordinator(): StartingFlowCoordinator
}
