package feature.data.datasource.category

import feature.data.model.Category

class DummyCategoryDataSource: CategoryDataSource {
    override fun getCategories(): List<Category> {
        return listOf(
            Category(
                name = "Drink",
                imgUrl = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/category/img_ic_drink.png"
            ),
            Category(
                name = "Noodle",
                imgUrl = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/category/img_ic_noodle.png"
            )

        )
    }
}