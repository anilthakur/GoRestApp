package com.anil.gorestapp.register.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.register.view.AppLockActivity
import com.anil.gorestapp.register.viewmodel.AppLockViewModel
import com.anil.gorestapp.register.viewmodel.AppLockViewModelFactory
import com.anil.gorestapp.register.viewmodel.AppLockViewModelmpl
import com.anil.gorestapp.register.widget.AppLockWidget
import com.anil.gorestapp.register.widget.AppLockWidget.*
import com.anil.gorestapp.register.widget.AppLockWidgetImpl
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


    @Provides
    @PerActivity
    fun provideAPpLocakViewModel(
        appLockActivity: AppLockActivity,
        factory: AppLockViewModelFactory
    ): AppLockViewModel =
        ViewModelProvider(appLockActivity, factory).get(AppLockViewModel::class.java)

    @Provides
    @PerActivity
    fun bindAppLocakViewModel(appLockViewModel: AppLockViewModelmpl): ViewModel =
        appLockViewModel

//    @Provides
//    @PerActivity
//    fun provideStateLiveData(): MutableLiveData<State> = MutableLiveData()

}