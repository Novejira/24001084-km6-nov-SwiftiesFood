package feature.home

import androidx.lifecycle.ViewModel
import feature.data.repository.CategoryRepository
import feature.data.repository.MenuRepository

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val menuRepository: MenuRepository
) : ViewModel(){
    fun getMenus() = menuRepository.getMenus()
    fun getCategories() = categoryRepository.getCategories()


}