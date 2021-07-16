package kg.sunrise.bimed.network.repositories

import kg.sunrise.bimed.base.repository.BaseRepository
import kg.sunrise.bimed.network.api.AuthAPI

class AuthRepository(
    private val authAPI: AuthAPI
): BaseRepository() {

    suspend fun enterWithPhoneNumber(phoneNumber: String) = makeRequest {
        authAPI.enterWithPhoneNumber(phoneNumber)
    }
}