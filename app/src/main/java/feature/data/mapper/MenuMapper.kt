package feature.data.mapper

import feature.data.model.Menu
import feature.data.source.network.MenuItemResponse

fun MenuItemResponse?.toMenu() =
    Menu(
        name = this?.name.orEmpty(),
        price = this?.price ?: 0.0,
        imgUrl = this?.imgUrl.orEmpty(),
        desc = this?.desc.orEmpty(),
        addres = this?.address.orEmpty(),
    )

fun Collection<MenuItemResponse>?.toMenus() = this?.map {
    it.toMenu()
} ?: listOf()