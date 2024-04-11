package feature.checkout

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.berkah.swiftiesfood.R
import feature.data.repository.CartRepository
import feature.presentation.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CheckoutViewModel (private val cartRepository: CartRepository) : ViewModel() {

    val checkoutData = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)

    fun checkoutDialog(context: Context){
        val dialogView:View =
            LayoutInflater.from(context).inflate(R.layout.view_dialog_checkout, null)

        val finishButton= dialogView.findViewById<Button>(R.id.button_ok)
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val dialog = builder.create()

        finishButton.setOnClickListener {

            dialog.dismiss()
            deleteAllCart()

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("fragment_to_load", "home") // Mengirimkan informasi fragment yang akan dimuat
            context.startActivity(intent)

        }
        dialog.show()
    }

    fun deleteAllCart(){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.deleteAll().collect()
        }
    }
}



