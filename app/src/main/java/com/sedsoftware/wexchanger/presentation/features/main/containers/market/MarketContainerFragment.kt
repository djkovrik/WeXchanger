package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.di.MarketContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_tab_container)
class MarketContainerFragment : BaseContainerFragment(), MarketContainerView {

  companion object {
    fun newInstance(tag: String?) = MarketContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }

  override val localNavigator: Navigator
    get() =
      Toothpick
        .openScope(AppScope.TAB_MARKET)
        .getInstance(Navigator::class.java)

  @InjectPresenter
  lateinit var presenter: MarketContainerPresenter

  @ProvidePresenter
  fun providePresenter(): MarketContainerPresenter {
    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_MARKET)
    scope.installModules(MarketContainerModule(this))
    Toothpick.inject(this, scope)
    return scope.getInstance(MarketContainerPresenter::class.java)
  }
}
