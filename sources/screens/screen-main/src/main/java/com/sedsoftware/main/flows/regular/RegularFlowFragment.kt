package com.sedsoftware.main.flows.regular

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.screens.main.R
import kotlinx.android.synthetic.main.fragment_flow_regular.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class RegularFlowFragment : FlowFragment() {

    companion object {
        fun newInstance(): RegularFlowFragment = RegularFlowFragment()

        private val walletTab = Screens.WalletTabContainer      // 0
        private val ordersTab = Screens.OrdersTabContainer      // 1
        private val marketTab = Screens.MarketTabContainer      // 2
        private val trackerTab = Screens.TrackerTabContainer    // 3
        private val toolsTab = Screens.ToolsTabContainer        // 4

        private val DEFAULT_TAB = R.id.navigation_market
    }

    override val layoutResId: Int = R.layout.fragment_flow_regular
    override val launchScreen: SupportAppScreen = Screens.MarketTabContainer

    @Inject
//    @RegularFlow
    lateinit var router: Router

    @Inject
//    @RegularFlow
    override lateinit var navigatorHolder: NavigatorHolder

    override val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.regularFlowContainer) {
            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    private val currentTabFragment: BaseTabFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseTabFragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_wallet -> selectTab(walletTab)
                R.id.navigation_orders -> selectTab(ordersTab)
                R.id.navigation_tracker -> selectTab(trackerTab)
                R.id.navigation_tools -> selectTab(toolsTab)
                else -> selectTab(marketTab)
            }
        }

        if (savedInstanceState == null) {
            bottomNavigation.selectedItemId = DEFAULT_TAB
        }
    }

    private fun selectTab(tab: SupportAppScreen): Boolean {
        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tab.screenKey)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return false

        childFragmentManager.beginTransaction()
            .apply {
                if (newFragment == null) {
                    tab.fragment?.let { add(R.id.regularFlowContainer, it, tab.screenKey) }
                }

                currentFragment?.let {
                    hide(it)
                }
                newFragment?.let {
                    show(it)
                }
            }.commitNow()

        return true
    }
}