package com.sedsoftware.screens.intro.exchanges.view

import android.os.Bundle
import android.view.View
import com.arkivanov.mvikotlin.extensions.androidx.lifecycle.asMviLifecycle
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.intro.exchanges.R
import com.sedsoftware.screens.intro.exchanges.controller.IntroExchangesController
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesBinding
import com.sedsoftware.screens.intro.exchanges.di.IntroExchangesComponent
import javax.inject.Inject

class IntroExchangesFragment :
    BaseFragment(R.layout.fragment_intro_exchanges), HasDaggerComponent<IntroExchangesComponent>, HasInject {

    companion object {
        fun newInstance(): IntroExchangesFragment = IntroExchangesFragment()
    }

    private val binding: FragmentIntroExchangesBinding by viewBinding()

    @Inject
    lateinit var assetsProvider: AssetsProvider

    @Inject
    lateinit var resourceManager: ResourceManager

    @Inject
    lateinit var controller: IntroExchangesController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = IntroExchangesViewImpl(binding, assetsProvider, resourceManager),
            lifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }

    override fun getComponent(): IntroExchangesComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return IntroExchangesComponent.Initializer.init(activityTools)
    }

    override fun inject() {
        DaggerComponentManager
            .get<IntroExchangesComponent>()
            .inject(this)
    }
}
