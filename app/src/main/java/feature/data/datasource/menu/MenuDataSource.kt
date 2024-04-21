package feature.data.datasource.menu

import feature.data.source.network.model.checkout.CheckoutRequestPayload
import feature.data.source.network.model.checkout.CheckoutResponse
import feature.data.source.network.model.menu.MenuResponse

interface MenuDataSource {
    suspend fun getMenus(categoryName: String? = null): MenuResponse

    suspend fun createOrder(payload: CheckoutRequestPayload): CheckoutResponse
}