package com.example.recyclerviewhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewhomework.fragments.ContentFragment
import com.example.recyclerviewhomework.fragments.MainFragment

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, mainFragment)
                    .commit()
            }
    }

}