package kg.sunrise.bimed.ui.auth

import kg.sunrise.bimed.base.viewModel.BaseViewModel
import kg.sunrise.bimed.network.repositories.AuthRepository
import kg.sunrise.bimed.utils.constants.PHONE_NUMBER
import kg.sunrise.bimed.utils.network.State

class AuthViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    fun enterWithPhoneNumber(phoneNumber: String) = getViewModelScope {
        // todo: configure request
//        val response = authRepository.enterWithPhoneNumber(phoneNumber)
//
//        if (!response.hasBody()) return@getViewModelScope
//
//        if (response!!.isSuccess()) {
            _state.value = State.SuccessState.SuccessObjectState<String>(PHONE_NUMBER)
//        }
    }
}