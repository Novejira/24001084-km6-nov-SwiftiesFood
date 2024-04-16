package com.catnip.layoutingexample.presentation.foodlist.adapter.adapter

class FoodGridItemViewHolder/*(
    private val binding: ItemFoodGridBinding,
    private val listener: MenuListAdapter.OnItemClickedListener<Menu>
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Menu> {

    override fun bind(item: Menu) {
        item.let {
            binding.ivFoodPhoto.load(it.imgURL) {
                crossfade(true)
                error(R.mipmap.ic_launcher)
            }
            binding.tvFoodName.text = it.name
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
            binding.tvFoodPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }

        }
    }
}**///