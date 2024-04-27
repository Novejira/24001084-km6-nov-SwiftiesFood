package feature.data.repository

import feature.data.datasource.category.CategoryDataSource
import feature.data.mapper.toCategories
import feature.data.model.Category
import feature.data.utils.ResultWrapper
import feature.data.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<ResultWrapper<List<Category>>>
}

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource,
) : CategoryRepository {
    override fun getCategories(): Flow<ResultWrapper<List<Category>>> {
        return proceedFlow { dataSource.getCategories().data.toCategories() }
    }
}
