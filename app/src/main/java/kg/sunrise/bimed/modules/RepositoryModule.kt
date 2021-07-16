package kg.sunrise.bimed.modules


import kg.sunrise.bimed.network.repositories.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
}