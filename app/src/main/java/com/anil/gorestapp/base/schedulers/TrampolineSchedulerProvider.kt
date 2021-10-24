package com.anil.gorestapp.base.schedulers

import com.anil.gorestapp.base.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerProvider : BaseSchedulerProvider {
    override fun computation():Scheduler = Schedulers.trampoline()
    override fun ui():Scheduler = Schedulers.trampoline()
    override fun io():Scheduler = Schedulers.trampoline()
}