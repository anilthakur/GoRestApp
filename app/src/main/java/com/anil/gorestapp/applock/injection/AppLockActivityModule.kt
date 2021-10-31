package com.anil.gorestapp.applock.injection

import android.content.Context
import com.anil.gorestapp.applock.view.AppLockActivity
import com.anil.gorestapp.applock.widget.AppLockWidget
import com.anil.gorestapp.applock.widget.AppLockWidget.*
import com.anil.gorestapp.applock.widget.AppLockWidgetImpl
import com.anil.gorestapp.base.di.qualifier.ForActivity
import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.base.livedata.SingleLiveData
import dagger.Module
import dagger.Provides

@Module
class AppLockActivityModule {

    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: AppLockActivity): Context = activityInStore

    @Provides
    @PerActivity
    fun provideAPPLockWidget(appLockWidget: AppLockWidgetImpl): AppLockWidget = appLockWidget

    @Provides
    @PerActivity
    fun provideOnClickedLiveData(): SingleLiveData<CallToAction> = SingleLiveData()
}