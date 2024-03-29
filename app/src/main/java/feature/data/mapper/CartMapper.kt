package feature.data.mapper

import feature.data.model.Cart
import feature.data.source.entity.CartEntity


fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    menuId = this?.menuId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    menuName = this?.menuName.orEmpty(),
    menuPrice = this?.menuPrice ?: 0.0,
    menuImgUrl = this?.menuImgUrl.orEmpty()
)

fun CartEntity?.toCart() = Cart(
    id = this?.id,
    menuId = this?.menuId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    menuName = this?.menuName.orEmpty(),
    menuPrice = this?.menuPrice ?: 0.0,
    menuImgUrl = this?.menuImgUrl.orEmpty()
)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }