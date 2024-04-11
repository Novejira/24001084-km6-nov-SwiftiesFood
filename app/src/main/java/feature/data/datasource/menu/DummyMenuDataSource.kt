package feature.data.datasource.menu

import feature.data.model.Menu

class DummyMenuDataSource: MenuDataSource {
    override fun getMenus(): List<Menu> {
        return listOf(
            Menu(
                name = "Mi Ayam",
                imgURL = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/menu/img_mieayam.jpeg?raw=true",
                desc = "Mie tambah ayam tambah sawi ",
                price = 12.00,
                addres = " Jl. Kemakmuran No.105, Cilodong, Kec. Cilodong, Kota Depok, Jawa Barat 16413",
                mapURL = "https://g.co/kgs/jF8XzDD"
            ),
            Menu(
                name = "Boba",
                imgURL = "https://raw.githubusercontent.com/Novejira/SwiftiesFoodAsset/main/menu/img_boba.jpeg?raw=true",
                desc = "bulat bulat tekstur kenyal dan rasa manis",
                price = 15.00,
                addres = "Jl. Ruko Anggrek 1 No.18 Blok C1, Tirtajaya, Kec. Sukmajaya, Kota Depok, Jawa Barat 16412",
                mapURL = "https://g.co/kgs/EmBVGwR"
            )
        )
    }
}