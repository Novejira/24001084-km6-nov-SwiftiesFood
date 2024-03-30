package feature.data.datasource.menu

import feature.data.model.Menu

interface MenuDataSource {
    fun getMenus():List<Menu>
}