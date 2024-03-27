package feature.data.datasource.menu

import feature.data.model.Menu

class DummyMenuDataSource: MenuDataSource {
    override fun getMenus(): List<Menu> {
        return listOf(
            Menu(
                name = "Mi Ayam",
                imgURL = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/menu/img_mieayam.jpeg",
                desc = "Mie tambah ayam tambah sawi ",
                price = 12.000,
                addres = " Jl. Kemakmuran No.105, Cilodong, Kec. Cilodong, Kota Depok, Jawa Barat 16413",
                mapURL = ""
            ),
            Menu(
                name = "Boba",
                imgURL = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/menu/img_boba.jpeg",
                desc = "bulat bulat tekstur kenyal dan rasa manis",
                price = 15.000,
                addres = "Jl. Ruko Anggrek 1 No.18 Blok C1, Tirtajaya, Kec. Sukmajaya, Kota Depok, Jawa Barat 16412",
                mapURL = ""
            )
        )
    }
}