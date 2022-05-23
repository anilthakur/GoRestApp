package com.anil.gorestapp.base.di.module

import com.anil.gorestapp.register.injection.AppLockActivityBinding
import com.anil.gorestapp.home.injection.HomeActivityBinding
import com.anil.gorestapp.signin.injection.MainActivityBinding
import dagger.Module


@Module(includes = [ MainActivityBinding::class,
    AppLockActivityBinding::class,
    HomeActivityBinding::class])
abstract class ActivityModule