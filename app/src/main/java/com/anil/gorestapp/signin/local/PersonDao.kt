package com.anil.gorestapp.signin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.gorestapp.signin.entities.Person
import io.reactivex.Single


@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertPerson(person: Person): Long

    @Query("SELECT * FROM Person")
    fun getPerson(): Single<List<Person>>
}