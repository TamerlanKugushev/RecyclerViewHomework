package com.example.recyclerviewhomework.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewhomework.Communicator
import com.example.recyclerviewhomework.Item
import com.example.recyclerviewhomework.MyAdapter
import com.example.recyclerviewhomework.R
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {
    private var listItems: MutableList<Item> = mutableListOf()
    private lateinit var listener: OnListFragmentInteractionListener
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(listItems.isNotEmpty()){
                    val removedPosition = listItems.size-1
                    listItems.removeAt(removedPosition)
                    myAdapter.notifyItemRemoved(removedPosition)
                }
                else{
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.floatingActionButton.setOnClickListener {
            addItem()
            myAdapter.notifyItemInserted(listItems.size-1)
        }

        myAdapter = MyAdapter(listItems, listener)
        view.recyclerView.adapter = myAdapter
        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            view.recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            view.recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
    }

    private fun addItem(){
        listItems.add(Item(((listItems.size) + 1).toString()))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnListFragmentInteractionListener){
            listener = context
        }
        else throw ClassCastException("$context must implement OnListFragmentInteractionListener")
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }
}