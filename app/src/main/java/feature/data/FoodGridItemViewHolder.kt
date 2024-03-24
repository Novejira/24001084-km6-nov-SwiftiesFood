package com.catnip.layoutingexample.presentation.foodlist.adapter.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.ItemFoodGridBinding
import com.catnip.layoutingexample.layoutingexample.presentation.foodlist.adapter.OnItemClickedListener
import feature.base.ViewHolderBinder
import feature.data.model.Catalog

class FoodGridItemViewHolder(
    private val binding: ItemFoodGridBinding,
    private val listener: OnItemClickedListener<Catalog>
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Catalog> {

    override fun bind(item: Catalog) {
        item.let {
            binding.ivFoodPhoto.load(it.image) {
                crossfade(true)
                error(R.mipmap.ic_launcher)
            }
            binding.tvFoodName.text = it.name
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
            binding.tvFoodPrice.text = it.formattedPrice
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }

        }
    }
}