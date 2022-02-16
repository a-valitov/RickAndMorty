package com.avalitov.rickandmorty

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
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
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var ivPicture : ImageView = itemView.findViewById(R.id.iv_picture)

        //init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.tvName.text = characters[position].name

        Glide.with(holder.ivPicture)
            .load(characters[position].image)
            .placeholder(R.drawable.ic_character_placeholder)
            .error(R.drawable.ic_character_placeholder)
            .circleCrop()
            .into(holder.ivPicture);

        holder.cvCharacter.setOnClickListener { view ->
            val message = "kek $position" //editText.text.toString()
            val intent = Intent(view.context, DetailsActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", message)
            }
            startActivity(view.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

}