package com.anil.gorestapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.room.Room
import com.anil.gorestapp.base.base.schedulers.BaseSchedulerProvider
import com.anil.gorestapp.base.base.schedulers.SchedulerProvider
import com.anil.gorestapp.base.base.schedulers.TestSchedulerProvider
import com.anil.gorestapp.base.base.schedulers.TrampolineSchedulerProvider
import com.anil.gorestapp.base.database.DbConstants
import com.anil.gorestapp.base.database.PersonDatabase
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.person.local.PersonDao
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    fun providePersonDatabase(context: Context): PersonDatabase {
        return Room.databaseBuilder(context, PersonDatabase::class.java!!, DbConstants.PERSON_DB_NAME)
                .build()
    }


    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideTrampolineSchedulerProvider(): TrampolineSchedulerProvider {
        return TrampolineSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideTestSchedulerProvider(): TestSchedulerProvider {
        return TestSchedulerProvider(TestScheduler())
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


//    @Singleton
//    @Provides
//    fun providePersonRepoRepo(api: RetrofitService, dao: PersonDao, schedulerProvider: BaseSchedulerProvider,  isTest: Boolean): PersonRepo = PersonRepoImpl(api, dao, schedulerProvider,  isTest)

    @Provides
    @Singleton
    fun providePersonDatabaseDao(database: PersonDatabase): PersonDao = database.personDao()

    @Provides
    fun provideMediatorLiveData(): MediatorLiveData<BaseViewModel.State> {
        return MediatorLiveData<BaseViewModel.State>()
    }

}