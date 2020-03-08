package com.anil.gorestapp.base.base.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerProvider : BaseSchedulerProvider {
    override fun computation():Scheduler = Schedulers.trampoline()
    override fun ui():Scheduler = Schedulers.trampoline()
    override fun io():Scheduler = Schedulers.trampoline()
}