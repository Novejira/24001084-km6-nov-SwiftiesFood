package feature.data.datasource.category

import feature.data.source.network.model.category.CategoriesResponse
import feature.data.source.network.services.SwiftiesFoodApiService

class CategoryApiDataSource(
    private val service: SwiftiesFoodApiService,
) : CategoryDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}
