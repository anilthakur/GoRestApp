package com.android.artgallery.di.module

import com.anil.gorestapp.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Kumar on 2020-02-11
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


}