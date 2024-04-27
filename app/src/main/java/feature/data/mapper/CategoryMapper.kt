package feature.data.mapper

import feature.data.model.Category
import feature.data.source.network.model.category.CategoryItemResponse

fun CategoryItemResponse?.toCategory() =
    Category(
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
        categoryDesc = "",
    )

fun Collection<CategoryItemResponse>?.toCategories() = this?.map { it.toCategory() } ?: listOf()
