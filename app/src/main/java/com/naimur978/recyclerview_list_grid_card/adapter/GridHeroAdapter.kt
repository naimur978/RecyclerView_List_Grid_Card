package com.naimur978.recyclerview_list_grid_card.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naimur978.recyclerview_list_grid_card.R
import com.naimur978.recyclerview_list_grid_card.model.HeroModel

class GridHeroAdapter(val listHero:ArrayList<HeroModel>) : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {
    private lateinit var onItemClickedCallback:OnItemClickedCallback

    interface OnItemClickedCallback {
        fun onItemClicked(data:HeroModel)
    }

    fun setOnClickCallBack(onItemClickedCallback: OnItemClickedCallback){
        this.onItemClickedCallback = onItemClickedCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHeroAdapter.GridViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero,parent,false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridHeroAdapter.GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(listHero[position].photo)
                .apply(RequestOptions().override(350,350))
                .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemClickedCallback.onItemClicked(listHero[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class GridViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imgPhoto:ImageView = itemView.findViewById(R.id.img_item_photo)
    }

}