package com.example.roommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roommvvm.fragment.NameListFragment
import com.example.roommvvm.fragment.NewNameFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(),

    //Memanggil 2 buah fragment yang ada di package fragment
    NewNameFragment.OnFragmentInteractionListener,
    NameListFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //Kondisi jika savedInstanceState = null atau kosong
        if (savedInstanceState == null) {

      //Untuk memanggil fungsi goToStudentListFragment
            goToStudentListFragment()
        }

        Timber.plant(Timber.DebugTree())
    }

    // Untuk memanggil fungsi untuk menampilkan fragment name list
    override fun goToStudentListFragment() {
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

    // Untuk membuat perpindahan fragment
        transaction.replace(R.id.flContent, NameListFragment.newInstance())

    // Memulai transaksi
        transaction.commit()
    }

    override fun goToNewNameFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        // Memindahkan fragment
        transaction.replace(R.id.flContent, NewNameFragment.newInstance())

        //Memulai transaksi
        transaction.commit()
    }
}
