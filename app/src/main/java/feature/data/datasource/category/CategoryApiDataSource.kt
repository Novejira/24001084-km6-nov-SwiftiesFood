package feature.data.datasource.category

import feature.data.source.network.CategoriesResponse
import feature.data.source.network.SwiftiesFoodApiService

class CategoryApiDataSource (
    private val service: SwiftiesFoodApiService
) : CategoryDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}