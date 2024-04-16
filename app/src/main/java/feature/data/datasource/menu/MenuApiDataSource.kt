package feature.data.datasource.menu

import feature.data.source.network.MenuResponse
import feature.data.source.network.SwiftiesFoodApiService

class MenuApiDataSource (
    private val service: SwiftiesFoodApiService
) : MenuDataSource {
    override suspend fun getMenus(categoryName: String?): MenuResponse {
        return service.getProducts(categoryName)

    }

}