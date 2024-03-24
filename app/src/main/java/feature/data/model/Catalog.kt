package feature.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Catalog(
    var name : String,
    var image: String,
    var foodDesc: String,
    val formattedPrice: String,
    val addres :String,
    val mapURL : String
): Parcelable
