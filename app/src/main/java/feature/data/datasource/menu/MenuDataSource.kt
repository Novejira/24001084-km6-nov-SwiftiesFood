package feature.data.datasource.menu

import feature.data.source.network.MenuResponse

interface MenuDataSource {
    suspend fun getMenus(categoryName: String? = null): MenuResponse
}