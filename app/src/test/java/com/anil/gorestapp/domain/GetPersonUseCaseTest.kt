package com.anil.gorestapp.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.base.base.schedulers.TrampolineSchedulerProvider
import com.anil.gorestapp.base.domain.model.ErrorModel
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.data.repository.PersonRepo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class GetPersonUseCaseTest {
    private lateinit var subject: GetPersonUseCaseImpl
    private var schedulerProvider = TrampolineSchedulerProvider()

    @Mock
    lateinit var personRepo: PersonRepo
    @Mock
    lateinit var compositeDisposable: CompositeDisposable
    @Mock
    lateinit var getPersonLiveData: MutableLiveData<PersonUseCase.Result>
    @Mock
    lateinit var stateLiveDataObserver: Observer<PersonUseCase.Result>

    lateinit var response: ResultData

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subject = GetPersonUseCaseImpl(compositeDisposable, getPersonLiveData, personRepo, schedulerProvider)
        subject.resultLiveData().observeForever(stateLiveDataObserver)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }


    @Test
    fun testGetPersonUseCases_getPerson_Success() {
        //GIVEN
        response = ResultData.fromData(Person())
        given(personRepo.getPersonData(true)).willReturn(Observable.just(response))

        //WHEN
        subject.execute(true)

        //THEN
        verify(getPersonLiveData).value = PersonUseCase.Result.HasPersonData(response.data as Person)
    }

    @Test
    fun testGetPersonUseCases_getPerson_Error() {
        //GIVEN
        response = ResultData.fromError(errorModel = ErrorModel(key = Person.INSTANCE_ID, message = "", code = -1, responseCode = null, isServiceError = true))
        given(personRepo.getPersonData(true)).willReturn(Observable.just(response))

        //WHEN
        subject.execute(true)

        //THEN
        verify(getPersonLiveData).value = PersonUseCase.Result.Error(ErrorModel(key = Person.INSTANCE_ID, message = "", code = -1, responseCode = null, isServiceError = true))
    }
}