package com.anil.gorestapp.base.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val scheduler: TestScheduler) : BaseSchedulerProvider {
    override fun computation(): Scheduler = scheduler
    override fun ui():Scheduler = scheduler
    override fun io():Scheduler = scheduler
}