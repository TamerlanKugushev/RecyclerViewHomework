package com.example.recyclerviewhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewhomework.fragments.ContentFragment
import com.example.recyclerviewhomework.fragments.MainFragment
import kotlinx.android.synthetic.main.even_item_layout.view.*
import kotlinx.android.synthetic.main.odd_item_layout.view.*


private const val ODD: Int = 0
private const val EVEN: Int = 1

class MyAdapter(
    private var items: List<Item>,
    private val listener: MainFragment.OnListFragmentInteractionListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ODD) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.odd_item_layout, parent, false)
            OddViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.even_item_layout, parent, false)
             EvenViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val baseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(items[position])
        baseViewHolder.itemView.setOnClickListener {
            listener.onListFragmentInteraction(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Item)
    }


    inner class OddViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        override fun bind(item: Item) {
            itemView.textViewOdd.text = item.number
            itemView.setOnClickListener { }
        }


    }

    inner class EvenViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        override fun bind(item: Item) {
            itemView.textViewEven.text = item.number
            itemView.setOnClickListener { }
        }
    }
}