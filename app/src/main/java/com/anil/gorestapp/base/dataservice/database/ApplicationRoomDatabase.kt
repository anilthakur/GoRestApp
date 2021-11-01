package com.anil.gorestapp.base.dataservice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anil.gorestapp.base.database.DbConstants
import com.anil.gorestapp.home.monitor.entities.Monitor
import com.anil.gorestapp.home.monitor.entities.MonitorConverter
import com.anil.gorestapp.signin.local.PersonDao
import com.anil.gorestapp.signin.entities.Person
import com.anil.gorestapp.signin.entities.PersonConverter
import com.anil.gorestapp.signin.local.MonitorDao

@Database(entities = [Person::class, Monitor::class], version = DbConstants.PERSON_DB_VERSION)
@TypeConverters(PersonConverter::class, MonitorConverter::class)
abstract class ApplicationRoomDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun monitorDao(): MonitorDao

}