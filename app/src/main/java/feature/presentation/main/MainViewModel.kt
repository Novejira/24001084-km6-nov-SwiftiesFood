package feature.presentation.main

import androidx.lifecycle.ViewModel
import feature.data.repository.UserRepository

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun isUserLoggedIn() = repository.isLoggedIn()
}
