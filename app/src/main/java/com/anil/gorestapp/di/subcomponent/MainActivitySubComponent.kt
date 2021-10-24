package com.anil.gorestapp.di.subcomponent

import com.anil.gorestapp.person.injection.MainActivityActivityModule
import com.anil.gorestapp.person.view.view.MainActivity
import com.anil.gorestapp.person.view.view.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityActivityModule::class])
interface MainActivitySubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

    fun inject(mainFragment: MainFragment)
}