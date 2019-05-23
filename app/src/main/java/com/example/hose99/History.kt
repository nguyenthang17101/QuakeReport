package com.example.hose99

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_history.*


class History : AppCompatActivity() {


    var list = MutableList(20) {it}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_history)
        setupRecyclerView()
    }
     fun setupRecyclerView() {
         recyclerView.layoutManager = LinearLayoutManager(this)
         recyclerView.adapter = Adapter(list)
     }
    class Adapter(var list : MutableList<Int>): RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history,parent,false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder is ViewHolder) {

            }
        }
    }
}
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
