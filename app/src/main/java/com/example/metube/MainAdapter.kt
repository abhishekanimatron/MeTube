package com.example.metube

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    //number of items in the data provided
    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }

    //specifies individual content of a cell in the view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val video = homeFeed.videos[position]
        //on the holder changes the text to corresponding title
        holder.titleView.text = video.name
        holder.video = video
        holder.channelName.text = video.channel.name + "  ⚬  " + "24K Views\n3 days ago"
        Glide.with(holder.itemView.context).load(video.imageUrl).into(holder.thumbnailImage)
        Glide.with(holder.itemView.context).load(video.channel.profileImageUrl)
            .into(holder.channelImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //inflates view taking parent as the context
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }
}

class CustomViewHolder(view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {
    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"
    }

    //getting the item (here title) with its id from the xml file
    val titleView: TextView = view.findViewById(R.id.textView_videoTitle)
    val channelName: TextView = view.findViewById(R.id.textView_channelName)
    val thumbnailImage: ImageView = view.findViewById(R.id.imageView_videoThumbnail)
    val channelImage: ImageView = view.findViewById(R.id.imageView_channelProfile)

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseDetailsActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            view.context.startActivity(intent)
        }
    }
}