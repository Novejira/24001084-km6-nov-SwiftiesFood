package feature.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkah.swiftiesfood.databinding.ItemFoodGridBinding
import com.berkah.swiftiesfood.databinding.ItemFoodListBinding
import com.catnip.layoutingexample.presentation.foodlist.adapter.adapter.FoodGridItemViewHolder
import com.catnip.layoutingexample.presentation.foodlist.adapter.adapter.FoodListItemViewHolder
import feature.base.ViewHolderBinder
import feature.data.model.Menu

class MenuListAdapter (
    private val listener : OnItemClickedListener<Menu>,
    private val listMode: Int = MODE_LIST
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val MODE_LIST = 0
        const val MODE_GRID = 1
    }

    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Menu>() {
                override fun areItemsTheSame(
                    oldItem: Menu,
                    newItem: Menu
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Menu,
                    newItem: Menu
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }
        )

    fun submitData(data: List<Menu>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (listMode == MODE_GRID) FoodGridItemViewHolder(
            ItemFoodGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),listener
        ) else {
            FoodListItemViewHolder(
                ItemFoodListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Menu>).bind(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size


    interface OnItemClickedListener<T>{
        fun onItemClicked(item: T)
    }
}