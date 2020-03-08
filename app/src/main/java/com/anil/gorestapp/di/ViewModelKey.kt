package com.android.artgallery.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
/**
 * Created by Anil Kumar on 2020-02-11
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)