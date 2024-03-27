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
import feature.data.model.Menu
import feature.data.repository.CategoryRepository
import feature.data.repository.CategoryRepositoryImpl
import feature.data.repository.MenuRepository
import feature.data.repository.MenuRepositoryImpl
import feature.data.utils.GenericViewModelFactory
import feature.presentation.detailfood.DetailFoodActivity
import feature.presentation.home.adapter.MenuAdapter
import feature.presentation.home.adapter.OnItemClickedListener


class Homefragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private var adapter: MenuAdapter?=null
    private var isUsingGridMode:Boolean = false

    private val viewModel:HomeViewModel by viewModels {
        val menuDataSource = DummyMenuDataSource()
        val menuRepository: MenuRepository = MenuRepositoryImpl(menuDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository,menuRepository))

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
        viewModel.getCategories()
        setClickAction()
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
        val listMode = if (isUsingGrid) MenuAdapter.MODE_GRID else MenuAdapter.MODE_LIST
        adapter = MenuAdapter (
            listMode = listMode,
            listener = object : OnItemClickedListener<Menu> {
                override fun onItemClicked(item: Menu) {
                    //navigate to detail
                    navigateToMenu(item)
                }
            })
        binding.rvFoodList.apply {
            adapter = this@Homefragment.adapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        adapter?.submitData(viewModel.getMenus())
    }
    private fun navigateToMenu(item: Menu) {
        DetailFoodActivity.startActivity(requireContext(),
            Menu(
                name = "Boba",
                imgURL = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/menu/img_boba.jpeg",
                desc = "Ada Bulat bulat ",
                price = 15.000,
                addres = "Jl. Ruko Anggrek 1 No.18 Blok C1, Tirtajaya, Kec. Sukmajaya, Kota Depok, Jawa Barat 16412",
                mapURL = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
           ),
        )
    }
}