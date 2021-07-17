package kg.sunrise.bimed.modules

import kg.sunrise.bimed.ui.auth.AuthViewModel
import kg.sunrise.bimed.ui.main.basket.BasketViewModel
import kg.sunrise.bimed.ui.main.category.CategoryViewModel
import kg.sunrise.bimed.ui.main.home.HomeViewModel
import kg.sunrise.bimed.ui.main.menu.MenuViewModel
import kg.sunrise.bimed.ui.main.menu.feedback.FeedbackViewModel
import kg.sunrise.bimed.ui.main.qrCode.QrCodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { BasketViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { HomeViewModel() }
    viewModel { MenuViewModel() }
    viewModel { QrCodeViewModel() }
    viewModel { FeedbackViewModel() }
}