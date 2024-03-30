package feature.data.datasource.category

import feature.data.model.Category

interface CategoryDataSource {
    fun getCategories(): List<Category>
}