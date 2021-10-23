package com.android.artgallery.di.module

import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.view.widget.PersonWidgetImpl
import com.anil.gorestapp.preferences.AppPreference
import com.anil.gorestapp.preferences.AppPreferenceImpl
import dagger.Binds
import dagger.Module


@Module
abstract class ActivityModule {


    @Binds
    abstract fun providePersonWidget(personWidget: PersonWidgetImpl) : PersonWidget
    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference

}