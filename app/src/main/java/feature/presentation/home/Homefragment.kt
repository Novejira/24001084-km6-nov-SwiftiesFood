package feature.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.FragmentHomeBinding
import feature.data.model.Category
import feature.data.model.Menu
import feature.presentation.detailfood.DetailFoodActivity
import feature.presentation.home.adapter.CategoryListAdapter
import feature.presentation.home.adapter.MenuListAdapter
import feature.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class Homefragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var menuAdapter: MenuListAdapter

    private var gridLayoutManager: GridLayoutManager? = null

    private val homeViewModel: HomeViewModel by viewModel()

    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {
            // when category clicked
            getMenuData(it.name)
        }
    }

    private fun observeGridMode() {
        homeViewModel.isUsingGridMode.observe(viewLifecycleOwner) {
            setButtonText(it)
            bindMenuList(it)
        }
    }

    private fun bindMenuList(isUsingGrid: Boolean) {
        val columnCount = if (isUsingGrid) 2 else 1
        val listType = if (isUsingGrid) MenuListAdapter.MODE_GRID else MenuListAdapter.MODE_LIST

        gridLayoutManager = GridLayoutManager(requireContext(), columnCount)
        binding.rvMenuList.adapter = menuAdapter
        binding.rvMenuList.layoutManager = gridLayoutManager
        menuAdapter.listMode = listType
        menuAdapter.notifyDataSetChanged()
    }

    private fun bindAdapterMenu() {
        val listType = if (homeViewModel.isUsingGridMode.value == true) MenuListAdapter.MODE_GRID else MenuListAdapter.MODE_LIST
        menuAdapter =
            MenuListAdapter(
                object : MenuListAdapter.OnItemClickedListener<Menu> {
                    override fun onItemClicked(item: Menu) {
                        navigateToDetail(item)
                    }
                },
                listType,
            )
    }

    private fun changeListMode() {
        homeViewModel.changeGridMode()
    }

    private fun bindMenu(data: List<Menu>) {
        menuAdapter.submitData(data)
    }

    private fun setSwitchListener() {
        binding.btnChangeListMode.setOnCheckedChangeListener { btn, isChecked ->
            homeViewModel.setUsingGridMode(isChecked)
            changeListMode()
        }
    }

    private fun setButtonText(usingGridMode: Boolean) {
        val textResId = if (usingGridMode) R.string.text_list_mode else R.string.text_grid_mode
        binding.btnChangeListMode.setText(textResId)
    }

    private fun getMenuData(categoryName: String? = null) {
        homeViewModel.getMenus(categoryName).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data ->
                        bindMenu(data)
                    }
                },
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerViews() {
        binding.rvCategory.adapter = categoryAdapter
        bindAdapterMenu()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        getCategoryData()
        setSwitchListener()
        getMenuData(null)
        observeGridMode()
    }

    private fun getCategoryData() {
        homeViewModel.getCategories().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindCategoryList(data) }
                },
            )
        }
    }

    private fun bindCategoryList(data: List<Category>) {
        categoryAdapter.submitData(data)
    }

    private fun navigateToDetail(item: Menu) {
        val intent =
            Intent(requireContext(), DetailFoodActivity::class.java).apply {
                putExtra(DetailFoodActivity.EXTRA_MENU, item)
            }
        startActivity(intent)
    }
}
