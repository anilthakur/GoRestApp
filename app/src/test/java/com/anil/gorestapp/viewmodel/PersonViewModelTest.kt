package com.anil.gorestapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anil.gorestapp.base.domain.model.ErrorModel
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.domain.PersonUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Anil Kumar on 2020-03-08
 */
@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var subject: PersonViewModel

    @Mock
    private lateinit var getPersonCase: PersonUseCase

    @Mock
    private lateinit var personTypeStateLiveDataObserver: Observer<BaseViewModel.State>

    private val personTypeResponseLiveData: MediatorLiveData<BaseViewModel.State> = MediatorLiveData()

    private val getPersonTypeResponseLiveData: MutableLiveData<PersonUseCase.Result> = MutableLiveData()

    private lateinit var errorModel: ErrorModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        personTypeResponseLiveData.observeForever(personTypeStateLiveDataObserver)


        // GIVEN
        given(getPersonCase.resultLiveData()).willReturn(getPersonTypeResponseLiveData)


        // INITIALISE
        subject = PersonViewModelImpl(getPersonCase, personTypeResponseLiveData)

        // THEN
        then(getPersonCase).should().resultLiveData()
    }

    @Test
    fun test_Person_execute_success() {
        //GIVEN
        subject.getPersonData()

        // THEN
        then(personTypeStateLiveDataObserver).shouldHaveZeroInteractions()
    }

    @Test
    fun test_OffersType_null() {
        //GIVEN
        getPersonTypeResponseLiveData.value = null

        // THEN
        then(personTypeStateLiveDataObserver).shouldHaveZeroInteractions()
    }

    @Test
    fun test_OffersType_hasOffersType_Success() {

        val personData = Person()
        //GIVEN
        getPersonTypeResponseLiveData.value = PersonUseCase.Result.HasPersonData(personData)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Success(personData))
    }

    @Test
    fun test_PersonType_generic_error_Success() {
        //GIVEN
        errorModel = ErrorModel()
        getPersonTypeResponseLiveData.value = PersonUseCase.Result.Error(errorModel)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Error(errorModel))
    }

    @Test
    fun test_Person_generic_service_error_Success() {
        //GIVEN
        errorModel = ErrorModel(isServiceError = true)
        getPersonTypeResponseLiveData.value = PersonUseCase.Result.Error(errorModel)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Error(ErrorModel(isServiceError = true)))
    }


}