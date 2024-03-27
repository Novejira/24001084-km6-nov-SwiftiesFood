package feature.presentation.detailfood

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.berkah.swiftiesfood.databinding.ActivityDetailFoodBinding
import feature.data.model.Menu
import feature.data.utils.toIndonesianFormat

class DetailFoodActivity : AppCompatActivity() {

    companion object{

        const val EXTRAS_DETAIL_FOOD = " EXTRAS_DETAIL_FOOD"
        fun startActivity(context: Context, menu:Menu ){
            val intent = Intent(context, DetailFoodActivity::class.java)
            intent.putExtra(EXTRAS_DETAIL_FOOD,menu)
            context.startActivity(intent)
        }

    }

    private val binding:ActivityDetailFoodBinding by lazy {
        ActivityDetailFoodBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentData()

    }

    private fun getIntentData() {
        intent.extras?.getParcelable<Menu>(EXTRAS_DETAIL_FOOD)?.let {
            setMenuData(it)
        }
    }

    private fun setMenuData(menu: Menu) {
        menu?.let { item->
            binding.ivPhotoFood.load(item.imgURL){
                crossfade(true)
            }
        }
        binding.tvNameFood.text = menu.name
        binding.tvFoodPrice.text = menu.price.toIndonesianFormat()
        binding.tvFoodDesc.text = menu.desc
        binding.tvDescLoca.text = menu.addres
        binding.tvDescLoca.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(menu.mapURL))
            startActivity(i)
        }
    }

}