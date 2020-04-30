package com.example.roommvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommvvm.dao.StudentDao
import com.example.roommvvm.entity.Student

    // Class database ini digunakan untuk pengecekan penjelasan  sql pada waktu compile, dengan cara mendeklarasikan room database yang digunakan di aplikasi tsb
@Database(entities = arrayOf(Student::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    //Memasukkan data StudentDao ke fungsi
    abstract fun studentDao(): StudentDao

    //companion object untuk menyimpan semua object yang ada
    companion object {
        private var INSTANCE: AppDatabase? = null
        //fun getInstance untuk mengecek apakah db ada / tidak, jika belum akan dibuild terlebih dahulu
        fun getInstance(context: Context): AppDatabase? {

        //jika database tidak ada maka kondisi akan seperti ini
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                     //build database akan dijalankan
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
                }
            }
            return INSTANCE
        }

       // fungsi untuk menghapus db yang ada
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}