package com.ruchanokal.movieomdb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruchanokal.movieomdb.R
//* View Binding *//
//import com.ruchanokal.movieomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //* View Binding *//
    //private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //* View Binding *//

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)
        setContentView(R.layout.activity_main)

    }
}