package com.anil.gorestapp.home.monitor.injection

import com.anil.gorestapp.base.di.scope.PerFragment
import com.anil.gorestapp.books.injection.BooksFragmentModule
import com.anil.gorestapp.books.view.BooksFragment
import com.anil.gorestapp.home.monitor.MonitorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MonitorFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [MonitorFragmentModule::class])
    abstract fun bindMonitorFragment(): MonitorFragment
}