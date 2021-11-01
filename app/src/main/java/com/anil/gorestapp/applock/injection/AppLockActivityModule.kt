package com.anil.gorestapp.applock.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.applock.view.AppLockActivity
import com.anil.gorestapp.applock.viewmodel.AppLockViewModel
import com.anil.gorestapp.applock.viewmodel.AppLockViewModelFactory
import com.anil.gorestapp.applock.viewmodel.AppLockViewModelmpl
import com.anil.gorestapp.applock.widget.AppLockWidget
import com.anil.gorestapp.applock.widget.AppLockWidget.*
import com.anil.gorestapp.applock.widget.AppLockWidgetImpl
import com.anil.gorestapp.base.di.qualifier.ForActivity
import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.base.di.scope.PerFragment
import com.anil.gorestapp.base.livedata.SingleLiveData
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.base.viewmodel.BaseViewModel.*
import com.anil.gorestapp.books.view.BooksFragment
import com.anil.gorestapp.books.viewmodel.BooksViewModel
import com.anil.gorestapp.books.viewmodel.BooksViewModelFactory
import com.anil.gorestapp.books.viewmodel.BooksViewModelImpl
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