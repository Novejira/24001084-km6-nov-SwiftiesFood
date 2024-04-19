package feature.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.berkah.swiftiesfood.R
import com.berkah.swiftiesfood.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import feature.data.repository.UserRepositoryImpl
import feature.data.source.network.firebase.FirebaseAuthDataSourceImpl
import feature.data.utils.GenericViewModelFactory
import feature.presentation.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var isLogin = false

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        GenericViewModelFactory.create(createViewModel())
    }

    private fun createViewModel(): MainViewModel {
        val firebaseAuth = FirebaseAuth.getInstance()
        val dataSource = FirebaseAuthDataSourceImpl(firebaseAuth)
        val repo = UserRepositoryImpl(dataSource)
        return MainViewModel(repo)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNav()
        }

    private fun setUpBottomNav() {
        val navController= findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{controller, destination,arguments ->
            when (destination.id){
                R.id.menu_tab_profile -> {
                    if(!isLogin){
                        checkIfUserLogin()
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
    }
    private fun checkIfUserLogin() {
        lifecycleScope.launch {
            delay(1000)
            if (viewModel.isUserLoggedIn()) {
                isLogin = true
            } else {
                navigateToLogin()
            }
        }
    }

}
