package feature.data.repository

import feature.data.datasource.menu.MenuDataSource
import feature.data.mapper.toMenus
import feature.data.model.Menu
import feature.data.utils.ResultWrapper
import feature.data.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenus(categoryName : String? = null): Flow<ResultWrapper<List<Menu>>>
}
class MenuRepositoryImpl(
    private val dataSource: MenuDataSource
): MenuRepository{
    override fun getMenus(categoryName: String?): Flow<ResultWrapper<List<Menu>>> {
        return proceedFlow {
            dataSource.getMenus(categoryName).data.toMenus()
        }

    }

}
