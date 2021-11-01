package com.anil.gorestapp.signin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.gorestapp.home.monitor.entities.Monitor
import io.reactivex.Single

@Dao
interface MonitorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMonitorItem(monitor: Monitor): Long

    @Query("SELECT * FROM Monitor")
    fun getMonitorItem(): Single<List<Monitor>>
}