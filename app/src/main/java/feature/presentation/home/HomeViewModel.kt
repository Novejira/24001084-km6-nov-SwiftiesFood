package feature.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import feature.data.repository.CategoryRepository
import feature.data.repository.MenuRepository
import feature.data.repository.PreferenceRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val menuRepository: MenuRepository,
    private val PreferenceRepository: PreferenceRepository,
) : ViewModel() {
    fun isUsingGridMode() = PreferenceRepository.isUsingGridMode()

    fun setUsingGridMode(isUsingGridMode: Boolean) = PreferenceRepository.setUsingGridMode(isUsingGridMode)

    val isUsingGridMode = MutableLiveData<Boolean>(false)

    fun changeGridMode() {
        val currentValue = isUsingGridMode.value ?: false
        isUsingGridMode.postValue(!currentValue)
    }

    fun getMenus(categoryName: String? = null) = menuRepository.getMenus(categoryName).asLiveData(Dispatchers.IO)

    fun getCategories() = categoryRepository.getCategories().asLiveData(Dispatchers.IO)
}
