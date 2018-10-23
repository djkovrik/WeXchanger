package com.sedsoftware.core.di.holder

import com.sedsoftware.core.di.provider.ActivityToolsProvider

interface ActivityComponentHolder {
    fun getActivityComponent(): ActivityToolsProvider
}
