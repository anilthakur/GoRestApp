package com.anil.gorestapp.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anil.gorestapp.data.local.PersonDao
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.data.entities.PersonConverter

@Database(entities = [Person::class], version = DbConstants.PERSON_DB_VERSION)
@TypeConverters(PersonConverter::class)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

}