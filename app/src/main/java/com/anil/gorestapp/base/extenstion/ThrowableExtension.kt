package com.anil.gorestapp.base.extenstion

    fun Throwable.isNetworkError() = this is java.io.IOException