package com.avalitov.rickandmorty.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.rickandmorty.DetailsActivity
import com.avalitov.rickandmorty.R
import com.avalitov.rickandmorty.model.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


// new since Glide v4
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // leave empty for now
}

class CharacterAdapter(private val characters: ArrayList<Character>)
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView) {
        var cvCharacter : CardView = itemView.findViewById(R.id.cv_character)
        var tvName: TextView = itemView.findViewById(R.id.tv_character_name)
        var ivPicture : ImageView = itemView.findViewById(R.id.iv_character_picture)

        //init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.tvName.text = characters[position].name

        Glide.with(holder.ivPicture)
            .load(characters[position].image)
            .placeholder(R.drawable.ic_character_placeholder)
            .error(R.drawable.ic_character_placeholder)
            .circleCrop()
            .into(holder.ivPicture)

        holder.cvCharacter.setOnClickListener { view ->
            val character = characters[position]
            val intent = Intent(view.context, DetailsActivity::class.java).apply {
                putExtra("selected character", character)
            }
            startActivity(view.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

}