package com.anil.gorestapp.testutils


import com.google.gson.Gson
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.Mockito.times
import java.util.*

inline fun <reified T> fileToGson(clazz: Class<T>, fileName: String): T {
    val jsonString = readFileAsString(clazz, fileName)
    return Gson().fromJson<T>(jsonString, T::class.java)
}

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

fun readFileAsString(clazz: Class<*>, fileName: String): String {
    val inputStream = clazz.classLoader?.getResourceAsStream(fileName)
    val scanner = Scanner(inputStream).useDelimiter("\\A")
    return if (scanner.hasNext()) scanner.next() else ""
}

fun <T> BDDMockito.Then<T>.should(times: Int): T = should(times(times))

fun <T> BDDMockito.Then<T>.shouldOnce(): T = should(times(1))