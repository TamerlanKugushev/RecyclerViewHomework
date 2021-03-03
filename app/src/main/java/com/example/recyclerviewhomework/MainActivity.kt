package com.example.recyclerviewhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewhomework.fragments.ContentFragment
import com.example.recyclerviewhomework.fragments.MainFragment


class MainActivity : AppCompatActivity(), MainFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .apply {
                    replace(R.id.fragmentContainer, mainFragment)
                        .commit()
                }
        }

    }

    override fun onListFragmentInteraction(position: Int) {
        val contentFragment = ContentFragment()
        contentFragment.arguments = Bundle().apply {
            putInt(ContentFragment.ARG_CONTENT, position + 1)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, contentFragment)
            .addToBackStack(null)
            .commit()
    }


}