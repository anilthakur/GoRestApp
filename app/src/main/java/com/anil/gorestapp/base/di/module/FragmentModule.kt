package com.anil.gorestapp.base.di.module

import com.anil.gorestapp.question.injection.BooksFragmentBinding
import com.anil.gorestapp.home.monitor.injection.MonitorFragmentBinding
import dagger.Module

@Module(includes = [ BooksFragmentBinding::class,
    MonitorFragmentBinding::class])
abstract class FragmentModule