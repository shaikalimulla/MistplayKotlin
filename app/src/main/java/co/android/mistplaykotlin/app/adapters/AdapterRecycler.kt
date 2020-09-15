package co.android.mistplay.app.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.android.mistplaykotlin.R
import co.android.mistplay.app.models.DataModel
import java.util.*

class AdapterRecycler(private val context: Context, dataModelList: ArrayList<DataModel>) : RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val dataModelList: ArrayList<DataModel>?

    // Inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    // Binds the data to the image view in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataModelList!!.isEmpty() || position >= dataModelList.size) {
            return
        }

        //Set List title
        holder.title.text = dataModelList[position].listTitle
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.layoutManager = layoutManager
        holder.recyclerView.isNestedScrollingEnabled = true
        val imageAdapterRecycler = ImageAdapterRecycler(context, dataModelList[position].imageModelList)
        holder.recyclerView.adapter = imageAdapterRecycler
    }

    // Total number of items
    override fun getItemCount(): Int {
        return dataModelList?.size ?: 0
    }

    // Stores and recycles views as they are scrolled off screen
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.images_list)

    }

    // data is passed into the constructor
    init {
        this.dataModelList = dataModelList
    }
}