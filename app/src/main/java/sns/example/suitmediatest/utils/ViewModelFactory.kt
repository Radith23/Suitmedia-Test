package sns.example.suitmediatest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sns.example.suitmediatest.data.UserRepository
import sns.example.suitmediatest.di.UserInjection
import sns.example.suitmediatest.ui.thirdScreen.ThirdViewModel

class ViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            return ThirdViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(UserInjection.provideUserRepository())
        }.also { instance = it }
    }
}