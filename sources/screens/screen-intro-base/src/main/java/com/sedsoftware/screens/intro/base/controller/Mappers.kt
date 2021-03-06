package com.sedsoftware.screens.intro.base.controller

import com.sedsoftware.screens.intro.base.IntroBaseEvent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.LoadingState
import com.sedsoftware.screens.intro.base.view.IntroBaseView

internal object Mappers {
    val viewEventToIntent: IntroBaseView.ViewEvent.() -> IntroBaseStore.Intent = {
        when (this) {
            is IntroBaseView.ViewEvent.DownloadClicked -> IntroBaseStore.Intent.LoadCurrencyMap
            is IntroBaseView.ViewEvent.NextClicked -> IntroBaseStore.Intent.NavigateToNextScreen
        }
    }

    val stateToViewModel: IntroBaseStore.State.() -> IntroBaseView.ViewModel = {
        when (loadingState) {
            LoadingState.IDLE -> IntroBaseView.ViewModel(
                isDownloadButtonAvailable = true,
                isNextButtonAvailable = false,
                isProgressVisible = false
            )

            LoadingState.LOADING -> IntroBaseView.ViewModel(
                isDownloadButtonAvailable = false,
                isNextButtonAvailable = false,
                isProgressVisible = true
            )

            LoadingState.ERROR -> IntroBaseView.ViewModel(
                isDownloadButtonAvailable = true,
                isNextButtonAvailable = false,
                isProgressVisible = false
            )

            LoadingState.DONE -> IntroBaseView.ViewModel(
                isDownloadButtonAvailable = false,
                isNextButtonAvailable = true,
                isProgressVisible = false
            )
        }
    }

    val labelToEvent: IntroBaseStore.Label.() -> IntroBaseEvent = {
        when (this) {
            is IntroBaseStore.Label.ErrorCaught -> IntroBaseEvent.HandleError(throwable)
        }
    }
}
