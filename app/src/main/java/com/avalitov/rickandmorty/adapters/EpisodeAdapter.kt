package com.avalitov.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.R
import com.avalitov.rickandmorty.model.Episode

class EpisodeAdapter(private val episodes: ArrayList<Episode>)
    : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    class EpisodeViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView) {
        var cvEpisode : CardView = itemView.findViewById(R.id.cv_episode)
        var tvName: TextView = itemView.findViewById(R.id.tv_episode_name)
        var tvAirDate: TextView = itemView.findViewById(R.id.tv_episode_airDate)

        //init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_episode, parent, false)
        return EpisodeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.tvName.text = episodes[position].name
        holder.tvAirDate.text = episodes[position].air_date
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

}