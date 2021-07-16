package kg.sunrise.bimed.modules

import kg.sunrise.bimed.App.Companion.context
import kg.sunrise.bimed.network.api.AuthAPI
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit : Retrofit = createNetworkClient(context)

private val AUTH_API : AuthAPI = retrofit.create(AuthAPI::class.java)

val networkModule = module {
    single { AUTH_API }
}