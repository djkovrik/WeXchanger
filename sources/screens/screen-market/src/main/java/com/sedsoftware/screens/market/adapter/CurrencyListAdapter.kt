package com.sedsoftware.screens.market.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.screens.market.adapter.delegate.BaseCurrencyItemDelegate
import com.sedsoftware.screens.market.adapter.delegate.MarketCurrencyItemDelegate
import com.sedsoftware.screens.market.model.CurrencyListItem
import javax.inject.Inject

class CurrencyListAdapter @Inject constructor(
    clickListener: Listener
) : ListDelegationAdapter<List<CurrencyListItem>>() {

    interface Listener {
        fun onItemClick(item: CurrencyListItem)
    }

    init {
        delegatesManager
            .addDelegate(BaseCurrencyItemDelegate(clickListener))
            .addDelegate(MarketCurrencyItemDelegate(clickListener))
    }
}
