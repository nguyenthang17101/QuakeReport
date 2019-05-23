package com.example.quakereportx

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val urlString = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson"
    var list = arrayListOf<QuakeEvents>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        FecthJsonObject().execute(urlString)
    }
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter()
    }



    inner class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell,parent,false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ViewHolder) {
                holder.itemView.magnitude.text = "${list[position].mag}"
                holder.itemView.location.text = list[position].place
                holder.itemView.time.text = "${list[position].timeString}"
                holder.itemView.date.text = "${list[position].dateString}"

//                holder.itemView.magnitude.text = "${quakeEvents.magnitude}"
//                holder.itemView.driection.text = quakeEvents.driection
//                holder.itemView.location.text = quakeEvents.location
//                holder.itemView.time.text = quakeEvents.timeString
            }
        }
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    inner class FecthJsonObject : AsyncTask<String,Double,ArrayList<QuakeEvents>>() {
        override fun doInBackground(vararg params: String?): ArrayList<QuakeEvents> {
            return NetworkUtils.fetch(params.first() ?: "",::parse)
        }
        fun parse(input: String):ArrayList<QuakeEvents> {
            val baseJsonObject = JSONObject(input)
            val feature = baseJsonObject.getJSONArray("features")
            var result = arrayListOf<QuakeEvents>()
            for (index in 0 until feature.length()) {
                val quakeJson = feature.getJSONObject(index)
                val property = quakeJson.getJSONObject("properties")
                val mag = property.getDouble("mag")
                val place = property.getString("place")
                val time = property.getLong("time")
                val quakeEvents = QuakeEvents(mag,place,time)
                result.add(quakeEvents)
            }
            return result
        }

        override fun onPostExecute(result: ArrayList<QuakeEvents>?) {
            super.onPostExecute(result)
            Log.i("onPostExecute",result?.first()?.place)
            list = result ?: arrayListOf()
            recyclerView.adapter?.notifyDataSetChanged()
        }


    }
}

