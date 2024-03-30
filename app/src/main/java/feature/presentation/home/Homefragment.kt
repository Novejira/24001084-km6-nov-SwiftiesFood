package feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.FragmentHomeBinding
import feature.data.datasource.category.DummyCategoryDataSource
import feature.data.datasource.menu.DummyMenuDataSource
import feature.data.model.Category
import feature.data.model.Menu
import feature.data.repository.CategoryRepository
import feature.data.repository.CategoryRepositoryImpl
import feature.data.repository.MenuRepository
import feature.data.repository.MenuRepositoryImpl
import feature.data.utils.GenericViewModelFactory
import feature.presentation.detailfood.DetailFoodActivity
import feature.presentation.home.adapter.CategoryListAdapter
import feature.presentation.home.adapter.MenuListAdapter


class Homefragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    private var isUsingGridMode: Boolean = false

    private var adapter:MenuListAdapter?=null

    private val viewModel:HomeViewModel by viewModels {
        val menuDataSource = DummyMenuDataSource()
        val menuRepository: MenuRepository = MenuRepositoryImpl(menuDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository,menuRepository))

    }
    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {

        }
    }
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
        bindCategoryList(viewModel.getCategories())
        bindMenuList(isUsingGridMode)
        setClickAction()
    }
    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }
    private fun bindMenuList(isUsingGrid: Boolean) {
        val listMode = if (isUsingGrid) MenuListAdapter.MODE_GRID else MenuListAdapter.MODE_LIST
        adapter = MenuListAdapter(
            listMode = listMode,
            listener = object : MenuListAdapter.OnItemClickedListener<Menu> {
                override fun onItemClicked(item: Menu) {
                    //navigate to detail
                        DetailFoodActivity.startActivity(requireContext(),item)

                }
            })
        binding.rvMenuList.apply {
            adapter = this@Homefragment.adapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        adapter?.submitData(viewModel.getMenus())
    }

    private fun setClickAction() {
        binding.btnChangeListMode.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonText(isUsingGridMode)
            bindMenuList(isUsingGridMode)
        }
    }
    private fun setButtonText(usingGridMode: Boolean) {
        val textResId = if (usingGridMode) R.string.text_list_mode else R.string.text_grid_mode
        binding.btnChangeListMode.setText(textResId)
    }
}