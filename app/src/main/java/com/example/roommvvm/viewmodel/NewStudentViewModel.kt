package com.example.roommvvm.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {

   //Mendeklarasi AppDatabase
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>()

    //Fungsi untuk menyimpan data (insert data ke database)
    fun storeMovie(title: String) {

        val student = Student()
        student.name = title

        //Memulai  query input yg tersimpan dalam StudentDao
        GlobalScope.launch {
            mDb?.studentDao()?.insertStudent(student)
        }
    }

    fun retrieveStudent(): LiveData<List<Student>> {

      //Query select yg tersave di dalam StudentDao
        GlobalScope.launch {
            val list = mDb?.studentDao()?.getAll()

            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        return allStudent
    }
}