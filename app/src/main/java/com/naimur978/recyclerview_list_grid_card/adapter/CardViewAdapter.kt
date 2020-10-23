package com.naimur978.recyclerview_list_grid_card.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naimur978.recyclerview_list_grid_card.R
import com.naimur978.recyclerview_list_grid_card.model.HeroModel

class CardViewAdapter(private val listHero: ArrayList<HeroModel>) : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        val btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        val btnShare: Button = itemView.findViewById(R.id.btn_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val hero: HeroModel = listHero[position]
        Glide.with(holder.itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350, 350))
                .into(holder.imgPhoto)
        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail
        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Favorite" + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }
        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Share"+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"You selected ${listHero[holder.adapterPosition].name}",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listHero.size
    }
}