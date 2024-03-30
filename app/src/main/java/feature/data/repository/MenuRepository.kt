package feature.data.repository

import feature.data.datasource.menu.MenuDataSource
import feature.data.model.Menu

interface MenuRepository {
    fun getMenus(): List<Menu>
}
class MenuRepositoryImpl(private val dataSource: MenuDataSource): MenuRepository{
    override fun getMenus(): List<Menu> = dataSource.getMenus()

    }
