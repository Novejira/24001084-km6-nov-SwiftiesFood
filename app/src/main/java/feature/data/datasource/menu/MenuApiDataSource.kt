package feature.data.datasource.menu

import feature.data.source.network.model.checkout.CheckoutRequestPayload
import feature.data.source.network.model.checkout.CheckoutResponse
import feature.data.source.network.model.menu.MenuResponse
import feature.data.source.network.services.SwiftiesFoodApiService

class MenuApiDataSource(
    private val service: SwiftiesFoodApiService,
) : MenuDataSource {
    override suspend fun getMenus(categoryName: String?): MenuResponse {
        return service.getProducts(categoryName)
    }

    override suspend fun createOrder(payload: CheckoutRequestPayload): CheckoutResponse {
        return service.createOrder(payload)
    }
}
