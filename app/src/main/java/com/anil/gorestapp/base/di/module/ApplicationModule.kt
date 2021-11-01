package com.anil.gorestapp.base.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.room.Room
import com.anil.gorestapp.base.base.schedulers.BaseSchedulerProvider
import com.anil.gorestapp.base.base.schedulers.SchedulerProvider
import com.anil.gorestapp.base.base.schedulers.TestSchedulerProvider
import com.anil.gorestapp.base.base.schedulers.TrampolineSchedulerProvider
import com.anil.gorestapp.base.database.DbConstants
import com.anil.gorestapp.base.dataservice.database.ApplicationRoomDatabase
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.base.di.scope.PerApplication
import com.anil.gorestapp.signin.local.MonitorDao
import com.anil.gorestapp.signin.local.PersonDao
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler

@Module
class ApplicationModule {
    @PerApplication
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @PerApplication
    fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @PerApplication
    fun providePersonDatabase(context: Context): ApplicationRoomDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationRoomDatabase::class.java!!,
            DbConstants.PERSON_DB_NAME
        )
            .build()
    }


    @Provides
    @PerApplication
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @PerApplication
    fun provideTrampolineSchedulerProvider(): TrampolineSchedulerProvider {
        return TrampolineSchedulerProvider()
    }

    @Provides
    @PerApplication
    fun provideTestSchedulerProvider(): TestSchedulerProvider {
        return TestSchedulerProvider(TestScheduler())
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


//    @PerApplication
//    @Provides
//    fun providePersonRepoRepo(api: RetrofitService, dao: PersonDao, schedulerProvider: BaseSchedulerProvider,  isTest: Boolean): PersonRepo = PersonRepoImpl(api, dao, schedulerProvider,  isTest)

    @Provides
    @PerApplication
    fun providePersonDatabaseDao(database: ApplicationRoomDatabase): PersonDao = database.personDao()

    @Provides
    @PerApplication
    fun provideMonitorDatabaseDao(database: ApplicationRoomDatabase): MonitorDao = database.monitorDao()

    @Provides
    fun provideMediatorLiveData(): MediatorLiveData<BaseViewModel.State> {
        return MediatorLiveData<BaseViewModel.State>()
    }



}