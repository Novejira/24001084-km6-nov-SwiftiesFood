package feature.DI

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import feature.cart.CartViewModel
import feature.checkout.CheckoutViewModel
import feature.data.datasource.cart.CartDataSource
import feature.data.datasource.cart.CartDatabaseDataSource
import feature.data.datasource.category.CategoryApiDataSource
import feature.data.datasource.category.CategoryDataSource
import feature.data.datasource.menu.MenuApiDataSource
import feature.data.datasource.menu.MenuDataSource
import feature.data.datasource.user.UserDataSource
import feature.data.datasource.user.UserDataSourceImpl
import feature.data.repository.CartRepository
import feature.data.repository.CartRepositoryImpl
import feature.data.repository.CategoryRepository
import feature.data.repository.CategoryRepositoryImpl
import feature.data.repository.MenuRepository
import feature.data.repository.MenuRepositoryImpl
import feature.data.repository.PreferenceRepository
import feature.data.repository.PreferenceRepositoryImpl
import feature.data.repository.UserRepository
import feature.data.repository.UserRepositoryImpl
import feature.data.source.local.dao.CartDao
import feature.data.source.local.database.AppDatabase
import feature.data.source.local.pref.UserPreference
import feature.data.source.local.pref.UserPreferenceImpl
import feature.data.source.network.firebase.FirebaseAuthDataSource
import feature.data.source.network.firebase.FirebaseAuthDataSourceImpl
import feature.data.source.network.services.SwiftiesFoodApiService
import feature.presentation.detailfood.DetailFoodViewModel
import feature.presentation.home.HomeViewModel
import feature.presentation.login.LoginViewModel
import feature.presentation.main.MainViewModel
import feature.presentation.register.RegisterViewModel
import feature.profile.ProfileViewModel
import feature.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AppModules {
    private val networkModule =
        module {
            single<SwiftiesFoodApiService> { SwiftiesFoodApiService.invoke() }
        }
    private val firebaseModule =
        module {
            single<FirebaseAuth> { FirebaseAuth.getInstance() }
        }
    private val localModule =
        module {
            single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
            single<CartDao> { get<AppDatabase>().cartDao() }
            single<SharedPreferences> {
                SharedPreferenceUtils.createPreference(
                    androidContext(),
                    UserPreferenceImpl.PREF_NAME,
                )
            }
            single<UserPreference> { UserPreferenceImpl(get()) }
        }
    private val datasource =
        module {
            single<CartDataSource> { CartDatabaseDataSource(get()) }
            single<CategoryDataSource> { CategoryApiDataSource(get()) }
            single<MenuDataSource> { MenuApiDataSource(get()) }
            single<UserDataSource> { UserDataSourceImpl(get()) }
            single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
        }

    private val repository =
        module {
            single<CartRepository> { CartRepositoryImpl(get()) }
            single<CategoryRepository> { CategoryRepositoryImpl(get()) }
            single<MenuRepository> { MenuRepositoryImpl(get()) }
            single<UserRepository> { UserRepositoryImpl(get()) }
            single<PreferenceRepository> { PreferenceRepositoryImpl(get()) }
        }
    private val viewModelModule =
        module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::MainViewModel)
            viewModelOf(::CartViewModel)
            viewModelOf(::CheckoutViewModel)
            viewModelOf(::ProfileViewModel)
            viewModel {
                RegisterViewModel(get())
            }

            viewModel {
                LoginViewModel(get())
            }

            viewModel { params ->
                DetailFoodViewModel(
                    extras = params.get(),
                    cartRepository = get(),
                )
            }
        }

    val modules =
        listOf(
            networkModule,
            localModule,
            datasource,
            repository,
            firebaseModule,
            viewModelModule,
        )
}
