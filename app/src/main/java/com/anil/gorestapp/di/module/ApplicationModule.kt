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
import com.anil.gorestapp.data.local.PersonDao
import com.anil.gorestapp.data.repository.PersonRepo
import com.anil.gorestapp.data.repository.PersonRepoImpl
import com.anil.gorestapp.data.remote.RetrofitService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import javax.inject.Singleton
import com.anil.gorestapp.MainApplication
import com.anil.gorestapp.utils.properties.AppProperties


/**
 * Created by Anil Kumar on 2020-03-07
 */
@Module(includes = [PersonModule::class])
class ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }


    @Singleton
    @Provides
    fun provideAppProperties(): AppProperties {
        val sharedPreferences = MainApplication.applicationContext().getSharedPreferences("APPLICATION_SECURED_PREF", Context.MODE_PRIVATE)
        return AppProperties(sharedPreferences)
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


    @Singleton
    @Provides
    fun provideOfferRepo(api: RetrofitService, dao: PersonDao, schedulerProvider: BaseSchedulerProvider,  isTest: Boolean): PersonRepo = PersonRepoImpl(api, dao, schedulerProvider,  isTest)

    @Provides
    @Singleton
    fun provideTescoNewsDao(database: PersonDatabase): PersonDao = database.personDao()

    @Provides
    fun provideMediatorLiveData(): MediatorLiveData<BaseViewModel.State> {
        return MediatorLiveData<BaseViewModel.State>()
    }

}