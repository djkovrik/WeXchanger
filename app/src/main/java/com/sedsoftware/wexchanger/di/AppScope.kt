package com.sedsoftware.wexchanger.di

/**
 * Scope tree:
 *           /-> Tab 1
 * App -> Tab 2
 *           \-> Tab N
 */

object AppScope {
  const val APPLICATION = "APPLICATION"
  const val TAB_MARKET = "TAB_MARKET"
  const val TAB_ORDERS = "TAB_ORDERS"
  const val TAB_WALLET = "TAB_WALLET"
  const val TAB_TRACKER = "TAB_TRACKER"
}
