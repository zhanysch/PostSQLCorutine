package com.example.postrequesthttp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.postrequesthttp.data.model.ProfileModel

const val DATABASE_NAME = "auth_app"  //имя Б/Д

@Database(entities = [ProfileModel:: class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun profileDao():ProfileDao

    companion object{
        val MIGTRATION_1_2 : Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
               database.execSQL("ALTER TABLE ProfileModel ADD COLUMN avatar2 TEXT NOT NULL DEFAULT 's' ")
            }

        }
    }
}