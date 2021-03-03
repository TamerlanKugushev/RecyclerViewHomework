package com.example.recyclerviewhomework.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewhomework.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.lang.ClassCastException

private const val SAVED_RECYCLER_VIEW_STATUS_ID = "SAVED_RECYCLER_VIEW_STATUS_ID"
private const val SAVED_RECYCLER_VIEW_DATA_SET_ID = "SAVED_RECYCLER_VIEW_DATASET_ID"

class MainFragment : Fragment() {
    var listItems: ArrayList<Item> = ArrayList()
    lateinit var myAdapter: MyAdapter
    private lateinit var listener: OnListFragmentInteractionListener


    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (listItems.isNotEmpty()) {
                        val removedPosition = listItems.size - 1
                        listItems.removeAt(removedPosition)
                        myAdapter.notifyItemRemoved(removedPosition)
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })

        if (savedInstanceState != null) {
            listItems = savedInstanceState.getParcelableArrayList(SAVED_RECYCLER_VIEW_DATA_SET_ID)!!
        } else {
            listItems = ArrayList()
        }





        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.floatingActionButton.setOnClickListener {
            addItem()
            myAdapter.notifyItemInserted(listItems.size - 1)
        }

        myAdapter = MyAdapter(listItems, listener)

        view.recyclerViewMainFragment?.adapter = myAdapter
        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            view.recyclerViewMainFragment?.layoutManager = GridLayoutManager(context, 3)

        } else {
            view.recyclerViewMainFragment?.layoutManager = GridLayoutManager(context, 4)
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun addItem() {
        listItems.add(Item(((listItems.size) + 1).toString()))
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else throw ClassCastException("$context must implement OnListFragmentInteractionListener")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(SAVED_RECYCLER_VIEW_DATA_SET_ID, listItems)
        super.onSaveInstanceState(outState)
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }
}
