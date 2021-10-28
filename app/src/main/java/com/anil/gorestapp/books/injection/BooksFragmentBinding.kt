package com.anil.gorestapp.books.injection

import com.anil.gorestapp.books.view.BooksFragment
import com.anil.gorestapp.base.di.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BooksFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [BooksFragmentModule::class])
    abstract fun bindBooksFragment(): BooksFragment

}