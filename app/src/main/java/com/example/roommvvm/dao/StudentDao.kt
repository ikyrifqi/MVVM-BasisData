package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

  //DAO (Data Access Object) : interface semua query ditempatkan
@Dao

  //Membuat suatu interface dengan nama StudentDao untuk menghubungkan/mengakses database yang telah dibuat
interface StudentDao {

  //Sebuah query untuk menampilkan semua data dari table student
    @Query("Select * from student")
    fun getAll(): List<Student>

  //Sebuah query untuk memasukkan data ke table student
    @Insert
    fun insertStudent(item: Student)
}