package feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.FragmentHomeBinding
import com.catnip.layoutingexample.layoutingexample.data.FoodDataSource
import com.catnip.layoutingexample.layoutingexample.data.FoodDataSourceImpl
import com.catnip.layoutingexample.layoutingexample.presentation.foodlist.adapter.FoodAdapter
import com.catnip.layoutingexample.layoutingexample.presentation.foodlist.adapter.OnItemClickedListener
import feature.data.adapter.CategoryAdapter
import feature.data.model.Catalog
import feature.data.model.Category


class Homefragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private var adapter: FoodAdapter? = null
    private val dataSource: FoodDataSource by lazy { FoodDataSourceImpl() }
    private var isUsingGridMode: Boolean = false
    private val adapterCategory = CategoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickAction()
        setListCategory()
    }

    private fun setListCategory() {
        val data = listOf(
            Category(image = R.drawable.img_ic_noodle, name = "Noodle"),
            Category(image = R.drawable.img_ic_drink, name = "Drink")
        )
        binding.rvCategory.apply {
            adapter = adapterCategory
        }
        adapterCategory.submitData(data)
    }

    private fun setClickAction() {
        binding.btnChangeListMode.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonText(isUsingGridMode)
            bindFoodList(isUsingGridMode)
        }
    }

    private fun setButtonText(usingGridMode: Boolean) {
        val textResId = if (usingGridMode) R.string.text_list_mode else R.string.text_grid_mode
        binding.btnChangeListMode.setText(textResId)
    }

    private fun bindFoodList(isUsingGrid: Boolean) {
        val listMode = if (isUsingGrid) FoodAdapter.MODE_GRID else FoodAdapter.MODE_LIST
        adapter = FoodAdapter (
            listMode = listMode,
            listener = object : OnItemClickedListener<Catalog> {
                override fun onItemClicked(item: Catalog) {
                    //navigate to detail
                }
            })
        binding.rvFoodList.apply {
            adapter = this@Homefragment.adapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        adapter?.submitData(dataSource.getFoodMembers())
    }

}