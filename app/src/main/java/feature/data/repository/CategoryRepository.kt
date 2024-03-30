package feature.data.repository

import feature.data.datasource.category.CategoryDataSource
import feature.data.model.Category

interface CategoryRepository {
    fun getCategories():List<Category>
}
class CategoryRepositoryImpl(private val dataSource: CategoryDataSource): CategoryRepository{
    override fun getCategories(): List<Category> = dataSource.getCategories()

    }
