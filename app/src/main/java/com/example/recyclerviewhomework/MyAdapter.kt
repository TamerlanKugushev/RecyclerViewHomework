package com.example.recyclerviewhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewhomework.fragments.ContentFragment
import kotlinx.android.synthetic.main.even_item_layout.view.*
import kotlinx.android.synthetic.main.odd_item_layout.view.*


private const val ODD: Int = 0
private const val EVEN: Int = 1

class MyAdapter(private var items: List<Item>, private val communicator: Communicator) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ODD) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.odd_item_layout, parent, false)
            return OddViewHolder(view, communicator)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.even_item_layout, parent, false)
            return EvenViewHolder(view, communicator)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ODD) {
            (holder as OddViewHolder).bind(items[position])
            holder.itemView.setOnClickListener {
                val activity: AppCompatActivity = it.context as AppCompatActivity
                val contentFragment = ContentFragment()
                val bundle = Bundle()
                bundle.putString("content", it.textViewOdd.text.toString())
                contentFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, contentFragment)
                    .addToBackStack(null)
                    .commit()
            }
        } else {
            (holder as EvenViewHolder).bind(items[position])
            holder.itemView.setOnClickListener {
                val activity: AppCompatActivity = it.context as AppCompatActivity
                val contentFragment = ContentFragment()
                val bundle = Bundle()
                bundle.putString("content", it.textViewEven.text.toString())
                contentFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, contentFragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].number.toInt() % 2 != 0) {
            ODD
        } else {
            EVEN
        }
    }


    inner class OddViewHolder(itemView: View, communicator: Communicator) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bind(item: Item) {
            itemView.textViewOdd.text = item.number
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            communicator.passData(adapterPosition)
        }


    }

    inner class EvenViewHolder(itemView: View, communicator: Communicator) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bind(item: Item) {
            itemView.textViewEven.text = item.number
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            communicator.passData(adapterPosition)
        }

    }
}