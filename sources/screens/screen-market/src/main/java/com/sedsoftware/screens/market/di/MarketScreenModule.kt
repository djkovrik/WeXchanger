package com.sedsoftware.screens.market.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.store.MarketStoreFactory
import dagger.Module
import dagger.Provides

@Module
object MarketScreenModule {

    @Provides
    @ScreenScope
    fun provideMarketStore(
        managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
    ): MarketStore =
        MarketStoreFactory(DefaultStoreFactory, managers).create()
}