package com.anil.gorestapp.home.monitor.injection

import android.content.Context
import com.anil.gorestapp.base.di.qualifier.ForFragment
import com.anil.gorestapp.base.di.scope.PerFragment
import com.anil.gorestapp.home.monitor.MonitorFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MonitorFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: MonitorFragment): Context = fragment.requireContext()

    @PerFragment
    @Provides
    @Named("PerFragment")
    open fun provideInt(): String {
        return "hello"
    }
}