package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roommvvm.R
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.helper.StudentRecyclerAdapter
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */

   // class NameListFragment  untuk menampilkan fragment (list nama)
class NameListFragment : Fragment() {

   //Membuat objek untuk listener
    private var listener: OnFragmentInteractionListener? = null

    //Mendeklarasikan ViewModel
    private lateinit var mViewModel: NewStudentViewModel

    //Oncreate membuat suatu data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     //Memanggil viewmodel yang dilalui sebuah variabel
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")

      //Menyimpan nilai Live data ke StudentRecyclerAdapter
            rvList.adapter = StudentRecyclerAdapter(it)
        })
    }

       //Fungsi onCreateView pemanggil view pertamakali ketika fragment menampilkan layoutnya
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }

    //Fungsi ini dipanggil sesaat setelah onCreateView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     // Layout yang digunakan yaitu linear
        rvList.layoutManager = LinearLayoutManager(activity)

     //Fungsi onclick untuk tambah data baru
        btnAdd.setOnClickListener {
             //Deklarasi instance dari class student dao
            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()

        //Memulai query yg ada dalam class student dao
            GlobalScope.launch {
                dao?.getAll()
            }

            listener?.goToNewNameFragment()
        }
    }

     //Fungsi yg sedang berjalan ketika fragment diaktifkan
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //Fungsi yang bergerak ketika fragment dimatikan
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToNewNameFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
