package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screens.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : MainActivityToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(
            appProvider: AppProvider,
            @BindsInstance snackbarDelegate: SnackbarDelegate,
            @BindsInstance navigationFlowDelegate: NavigationFlowDelegate
        ): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(
                appProvider: AppProvider,
                snackbarDelegate: SnackbarDelegate,
                navigationFlowDelegate: NavigationFlowDelegate
            ): MainActivityComponent {

                return DaggerMainActivityComponent
                    .factory()
                    .create(appProvider, snackbarDelegate, navigationFlowDelegate)
            }
        }
    }
}
