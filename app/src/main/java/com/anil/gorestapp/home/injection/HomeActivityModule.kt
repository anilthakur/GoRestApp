package com.anil.gorestapp.home.injection

import android.content.Context
import com.anil.gorestapp.base.di.qualifier.ForActivity
import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.home.home.injection.HomeFragmentBinding
import com.anil.gorestapp.home.monitor.injection.MonitorFragmentBinding
import com.anil.gorestapp.home.profile.injection.ProfileFragmentBinding
import com.anil.gorestapp.home.view.HomeActivity
import com.anil.gorestapp.signin.view.view.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [
        HomeFragmentBinding::class,
        MonitorFragmentBinding::class,
        ProfileFragmentBinding::class]
)
class HomeActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: HomeActivity): Context = activityInStore

    @PerActivity
    @Provides
    @Named("PerActivity1")
    open fun provideInt(): String {
        return "hello"
    }

}