package com.sedsoftware.wexchanger.presentation.features.orders.helper

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class OrdersHelperPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<OrdersHelperView>() {

  fun onBackPressed() {
    router.exit()
  }
}