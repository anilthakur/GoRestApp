package com.anil.gorestapp.home.monitor.entities

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anil.gorestapp.signin.entities.Person
import com.google.gson.annotations.Expose


@Entity
data class Monitor(
    @PrimaryKey
    @Expose
    var id: String = Person.INSTANCE_ID,
    val appName: String? = null,
    val parentUsageDuration: Long? = null,
    val childUsageDuration: Long? = null
)
