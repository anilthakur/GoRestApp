package com.anil.gorestapp.base.domain

import androidx.annotation.NonNull
import com.anil.gorestapp.base.base.domain.UseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseUseCase protected constructor(private val compositeDisposable: CompositeDisposable) : UseCase {
    
    override fun trackDisposable(@NonNull disposable: Disposable): Disposable {
        compositeDisposable.add(disposable)
        return disposable
    }
    
    override fun cleanup() {
        compositeDisposable.clear()
    }
}