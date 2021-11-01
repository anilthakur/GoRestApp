package com.anil.gorestapp.base.di.module

import com.anil.gorestapp.applock.injection.AppLockActivityBinding
import com.anil.gorestapp.home.injection.HomeActivityBinding
import com.anil.gorestapp.signin.view.widget.PersonWidget
import com.anil.gorestapp.signin.view.widget.PersonWidgetImpl
import com.anil.gorestapp.preferences.AppPreference
import com.anil.gorestapp.preferences.AppPreferenceImpl
import com.anil.gorestapp.signin.injection.MainActivityBinding
import dagger.Binds
import dagger.Module


@Module(includes = [ MainActivityBinding::class,
    AppLockActivityBinding::class,
    HomeActivityBinding::class])
abstract class ActivityModule