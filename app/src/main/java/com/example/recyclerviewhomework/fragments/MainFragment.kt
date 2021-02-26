package com.example.recyclerviewhomework.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewhomework.Communicator
import com.example.recyclerviewhomework.Item
import com.example.recyclerviewhomework.MyAdapter
import com.example.recyclerviewhomework.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {
    var listItems: MutableList<Item> = mutableListOf()
    lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)



        view.floatingActionButton.setOnClickListener {
            //listItems.add(Item(((listItems.size) + 1).toString()))
            addItem()
            myAdapter.notifyItemInserted(listItems.size-1)

        }



        myAdapter = MyAdapter(listItems, communicator)
        view.recyclerView.adapter = myAdapter
        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            view.recyclerView.layoutManager = GridLayoutManager(context, 3)

        } else {
            view.recyclerView.layoutManager = GridLayoutManager(context, 4)
        }



        return view
    }

    private fun addItem(){
        listItems.add(Item(((listItems.size) + 1).toString()))
    }

    private val communicator = (object : Communicator {
        override fun passData(position: Int) {
            val contentFragment = ContentFragment()
            val fragmentManager = activity?.supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer, contentFragment)
        }
    })
    


}