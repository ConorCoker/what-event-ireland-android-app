package com.example.whateventireland_androidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(private val mList: ArrayList<Event>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageDrawable(itemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textViewDetails.text = itemsViewModel.description

        holder.textViewTitle.text = itemsViewModel.title


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view_event_image)
        val textViewDetails: TextView = itemView.findViewById(R.id.text_view_event_details)
        val textViewTitle:TextView = itemView.findViewById(R.id.text_view_event_title)
    }
}