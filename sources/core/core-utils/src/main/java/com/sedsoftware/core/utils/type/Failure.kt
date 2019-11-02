package com.sedsoftware.core.utils.type

sealed class Failure {
    object NetworkConnectionMissing : Failure()
    class CurrencyMapLoadingError(val throwable: Throwable) : Failure()
    class PairsLoadingError(val throwable: Throwable) : Failure()
    class PairsManagerError(val throwable: Throwable) : Failure()
}
