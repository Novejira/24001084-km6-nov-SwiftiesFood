package feature.data.datasource.category

import feature.data.source.network.CategoriesResponse

interface CategoryDataSource {
    suspend fun getCategories(): CategoriesResponse
}