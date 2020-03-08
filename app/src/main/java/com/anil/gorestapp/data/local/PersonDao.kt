package com.anil.gorestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.gorestapp.data.entities.Person
import io.reactivex.Single

/**
 * Created by Anil Kumar on 2020-03-07
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertPerson(person: Person): Long

    @Query("SELECT * FROM Person")
    fun getPerson(): Single<List<Person>>
}