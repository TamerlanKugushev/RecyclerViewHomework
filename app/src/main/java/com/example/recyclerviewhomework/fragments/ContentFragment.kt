package com.example.recyclerviewhomework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerviewhomework.R
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_content.view.*


class ContentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_content, container, false)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val arguments: Bundle? = arguments
        if (arguments != null) {
            val content = arguments.get(ARG_CONTENT).toString()
            textViewContent.text = content
        }
        super.onActivityCreated(savedInstanceState)
    }


    companion object {
        const val ARG_CONTENT = "content"
    }


}