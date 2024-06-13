package sns.example.suitmediatest.di

import sns.example.suitmediatest.data.UserRepository
import sns.example.suitmediatest.network.ApiConfig

object UserInjection {
    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}