package feature.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkah.swiftiesfood.databinding.ItemFoodGridBinding
import com.berkah.swiftiesfood.databinding.ItemFoodListBinding
import feature.base.ViewHolderBinder
import feature.data.model.Menu

class MenuAdapter(
    private val listener: OnItemClickedListener<Menu>,
    private val listMode: Int = MODE_LIST
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val MODE_LIST = 0
        const val MODE_GRID = 1
    }

    private var asyncDataDiffer = AsyncListDiffer(
        this, object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                //membandingkan apakah item tersebut sama
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                // yang dibandingkan adalah kontennya
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    )

    fun submitData(data: List<Menu>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //membuat instance of view holder
        return if (listMode == MODE_GRID) MenuGridItemViewHolder(
            ItemFoodGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        ) else {
            MenuListItemViewHolder(
                ItemFoodListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Menu>).bind(asyncDataDiffer.currentList[position])
    }
}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}
