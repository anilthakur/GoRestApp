package com.anil.gorestapp.books.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.books.domain.GetBookUseCase
import com.anil.gorestapp.books.domain.GetBookUseCaseImpl
import com.anil.gorestapp.books.repository.BooksRepo
import com.anil.gorestapp.books.repository.BooksRepoImpl
import com.anil.gorestapp.books.view.BooksFragment
import com.anil.gorestapp.books.viewmodel.BooksViewModel
import com.anil.gorestapp.books.viewmodel.BooksViewModelFactory
import com.anil.gorestapp.books.viewmodel.BooksViewModelImpl
import com.anil.gorestapp.base.di.qualifier.ForFragment
import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.base.di.scope.PerFragment
import com.anil.gorestapp.books.view.widget.BooksWidget
import com.anil.gorestapp.books.view.widget.BooksWidgetImpl
import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.view.widget.PersonWidgetImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class BooksFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: BooksFragment): Context = fragment.requireContext()

    @Provides
    fun provideBooksRepository(booksRepo: BooksRepoImpl): BooksRepo = booksRepo

    @Provides
    internal fun provideUseCase(useCase: GetBookUseCaseImpl): GetBookUseCase = useCase

    @Provides
    @PerFragment
    fun provideBooksViewModel(
        fragment: BooksFragment,
        factory: BooksViewModelFactory
    ): BooksViewModel =
        ViewModelProvider(fragment, factory).get(BooksViewModel::class.java)

    @Provides
    @PerFragment
    fun bindBooksViewModel(booksViewModel: BooksViewModelImpl): ViewModel =
        booksViewModel

    @Provides
    @PerFragment
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()

    @PerFragment
    @Provides
    @Named("PerFragment")
    open fun provideInt(): String {
        return "hello"
    }

    @Provides
    @PerFragment
    fun provideBooksWidget(booksWidget: BooksWidgetImpl): BooksWidget = booksWidget

}