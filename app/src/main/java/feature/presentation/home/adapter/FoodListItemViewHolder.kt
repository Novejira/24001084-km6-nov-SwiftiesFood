package com.catnip.layoutingexample.presentation.foodlist.adapter.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.ItemFoodListBinding
import feature.base.ViewHolderBinder
import feature.data.model.Menu
import feature.data.utils.toIndonesianFormat
import feature.presentation.home.adapter.MenuListAdapter

class FoodListItemViewHolder(
    private val binding: ItemFoodListBinding,
    private val listener: MenuListAdapter.OnItemClickedListener<Menu>,
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Menu> {
    override fun bind(item: Menu) {
        item.let {
            binding.ivFoodPhoto.load(it.imgUrl) {
                crossfade(true)
                error(R.mipmap.ic_launcher)
            }
            binding.tvFoodName.text = it.name
            binding.tvFoodPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}
