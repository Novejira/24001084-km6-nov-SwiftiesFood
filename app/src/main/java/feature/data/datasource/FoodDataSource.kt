package com.catnip.layoutingexample.layoutingexample.data

import feature.data.model.Catalog

interface FoodDataSource {
    fun getFoodMembers(): List<Catalog>
}

class FoodDataSourceImpl() : FoodDataSource {
    override fun getFoodMembers(): List<Catalog> {
        return mutableListOf(
            Catalog(
                name = "Mie Ayam",
                formattedPrice = "Rp 12.000",
                image = "https://i.pinimg.com/564x/47/38/71/4738712e5cf28a688dbe776053a798e6.jpg",
                foodDesc = "Mie" ,
                addres = "Gg. Hj Senin No.06, RW.09, Kec. Sukmajaya, Kota Depok, Jawa Barat 16412",
                mapURL = ""
            ),
            Catalog(
                name = "Boba",
                formattedPrice = "Rp 15.000",
                image = "https://i.pinimg.com/564x/c4/85/62/c485623ea335cc7c19b6bf3aff1687dc.jpg",
                foodDesc = "Bulat",
                addres = "Jl. Ruko Anggrek 1 No.18 Blok C1, Tirtajaya, Kec. Sukmajaya, Kota Depok, Jawa Barat 16412",
                mapURL = ""

            ),
        )
    }
}