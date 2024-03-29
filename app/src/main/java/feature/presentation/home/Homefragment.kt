package feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import feature.data.utils.GridSpacingItemDecoration
import feature.presentation.detailfood.DetailFoodActivity
import feature.presentation.home.adapter.CategoryListAdapter
import feature.presentation.home.adapter.MenuListAdapter


class Homefragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

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
    private val menuAdapter: MenuListAdapter by lazy {
        MenuListAdapter {
            DetailFoodActivity.startActivity(requireContext(), it)
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
        bindMenuList(viewModel.getMenus())
    }
    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }
    private fun bindMenuList(data: List<Menu>) {
        val itemDecoration = GridSpacingItemDecoration(2, 12, true)
        binding.rvMenuList.apply {
            adapter = menuAdapter
            addItemDecoration(itemDecoration)
        }
        menuAdapter.submitData(data)
    }
}