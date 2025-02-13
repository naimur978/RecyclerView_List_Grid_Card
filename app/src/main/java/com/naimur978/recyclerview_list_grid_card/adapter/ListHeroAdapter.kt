package com.naimur978.recyclerview_list_grid_card.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naimur978.recyclerview_list_grid_card.R
import com.naimur978.recyclerview_list_grid_card.model.HeroModel

class ListHeroAdapter(private val listHero:ArrayList<HeroModel>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack:OnItemClickCallBack

    interface OnItemClickCallBack {
        fun onItemClicked(data:HeroModel)
    }

    fun setOnItemClickedCallBack(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_hero,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = listHero[position]
        Glide.with(holder.itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(55,55))
                .into(holder.imgPhoto)
        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listHero[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName:TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail:TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto:ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}
