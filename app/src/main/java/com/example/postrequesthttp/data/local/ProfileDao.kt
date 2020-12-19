package com.example.postrequesthttp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postrequesthttp.data.model.ProfileModel


@Dao   //class для запроса в Room
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveProfile(user: ProfileModel)

   @Query("SELECT * FROM ProfileModel")
   fun getProfile(): LiveData<ProfileModel>
}