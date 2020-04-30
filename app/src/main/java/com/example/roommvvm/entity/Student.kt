package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

      // Class entity digunakan untuk mendeklarasikan tabel beserta isinya (struktur/kolom) dalam db
@Entity
data class Student (
     //Deklarasi id sebagai primary key dan idnya bersifat autoincrement
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

     //Deklarasi tipe data di kolom name, kolom ini inputan dari user yang memakai app
    @ColumnInfo var name: String = ""
)