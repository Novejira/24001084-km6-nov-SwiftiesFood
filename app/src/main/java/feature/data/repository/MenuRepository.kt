package feature.data.repository

import feature.data.datasource.menu.MenuDataSource
import feature.data.mapper.toMenus
import feature.data.model.Cart
import feature.data.model.Menu
import feature.data.source.network.model.checkout.CheckoutItemPayload
import feature.data.source.network.model.checkout.CheckoutRequestPayload
import feature.data.utils.ResultWrapper
import feature.data.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenus(categoryName: String? = null): Flow<ResultWrapper<List<Menu>>>

    fun createOrder(menus: List<Cart>): Flow<ResultWrapper<Boolean>>
}

class MenuRepositoryImpl(
    private val dataSource: MenuDataSource,
) : MenuRepository {
    override fun getMenus(categoryName: String?): Flow<ResultWrapper<List<Menu>>> {
        return proceedFlow {
            dataSource.getMenus(categoryName).data.toMenus()
        }
    }

    override fun createOrder(menus: List<Cart>): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.createOrder(
                CheckoutRequestPayload(
                    orders =
                        menus.map {
                            CheckoutItemPayload(
                                notes = it.itemNotes,
                                menuId = it.menuId.orEmpty(),
                                quantity = it.itemQuantity,
                            )
                        },
                ),
            ).status ?: false
        }
    }
}
