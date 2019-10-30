package com.sedsoftware.screens.intro.exchanges

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.coordinator.FlowSwitcher
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.type.DownloadState
import com.sedsoftware.core.utils.extension.either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.screens.intro.exchanges.model.ExchangeListItem
import javax.inject.Inject

class IntroExchangesViewModel @Inject constructor(
    private val coordinator: FlowSwitcher,
    private val loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
) : BaseViewModel() {

    internal val exchangeList = MutableLiveData<List<ExchangeListItem>>()
    internal val anyDownloadCompleted = MutableLiveData<Boolean>()

    private val loadingStates = mutableMapOf<Exchange, DownloadState>()

    init {
        loaders.keys.forEach { exchange ->
            loadingStates[exchange] = DownloadState.AVAILABLE
        }
        emitCurrentList()
    }

    fun switchToRegularFlow() {
        coordinator.switchToRegularFlow()
    }

    fun onExchangeClicked(exchange: Exchange) {
        setExchangeState(exchange, DownloadState.IN_PROGRESS)

        launch {
            loaders[exchange]
                ?.fetchCurrencyPairs()
                ?.either({ handleLoadingError(it, exchange) }, { handleLoadingCompletion(exchange) })
        }
    }

    private fun setExchangeState(exchange: Exchange, state: DownloadState) {
        loadingStates[exchange] = state
        emitCurrentList()
    }

    private fun emitCurrentList() {
        exchangeList.value = loadingStates.map {
            ExchangeListItem(
                it.key,
                it.value
            )
        }
    }

    private fun handleLoadingError(failure: Failure, exchange: Exchange) {
        setExchangeState(exchange, DownloadState.ERROR)
        handleFailure(failure)
    }

    private fun handleLoadingCompletion(exchange: Exchange) {
        setExchangeState(exchange, DownloadState.COMPLETED)
        if (anyDownloadCompleted.value != true) {
            anyDownloadCompleted.value = true
        }
    }
}
