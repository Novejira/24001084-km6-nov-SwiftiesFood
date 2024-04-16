package feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import feature.data.repository.CategoryRepository
import feature.data.repository.MenuRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val menuRepository: MenuRepository
) : ViewModel(){
    fun getMenus(categoryName: String? = null) =
        menuRepository.getMenus(categoryName).asLiveData(Dispatchers.IO)
    fun getCategories() = categoryRepository.getCategories().asLiveData(Dispatchers.IO)
}