package co.android.mistplay.app.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.android.mistplaykotlin.R
import co.android.mistplay.app.models.ImageModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.util.*

class ImageAdapterRecycler(context: Context, imageModelList: ArrayList<ImageModel>) : RecyclerView.Adapter<ImageAdapterRecycler.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val imageModelList: ArrayList<ImageModel>?

    // Inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_images_view, parent, false)
        return ViewHolder(view)
    }

    // Binds the data to the image view in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (imageModelList!!.isEmpty() || position >= imageModelList.size) {
            return
        }

        //Set image title
        holder.imageTitle.text = imageModelList[position].imageTitle
        /*
        * We used Glide third party library to fetch image from url and load into image view.
        * We can perform fetching image from url using AsyncTask which runs in background or IntentService and then load it into image view.
        * The reason to use Glide over the above approach is performance and Glide caches images so it is enough to fetch data once.
         */
        val radius = holder.itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius)
        Glide.with(holder.itemView.context).load(imageModelList[position].imageUrl).transform(RoundedCorners(radius)).into(holder.imageView)
    }

    // Total number of items
    override fun getItemCount(): Int {
        return imageModelList?.size ?: 0
    }

    // Stores and recycles views as they are scrolled off screen
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageTitle: TextView = itemView.findViewById(R.id.image_title)
        var imageView: ImageView = itemView.findViewById(R.id.image_view)

    }

    // data is passed into the constructor
    init {
        this.imageModelList = imageModelList
    }
}