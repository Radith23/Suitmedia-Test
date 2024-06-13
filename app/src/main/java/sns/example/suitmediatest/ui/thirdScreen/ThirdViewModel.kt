package sns.example.suitmediatest.ui.thirdScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import sns.example.suitmediatest.data.UserRepository
import sns.example.suitmediatest.response.DataItem

class ThirdViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _listUser = MutableLiveData<PagingData<DataItem>>()
    val listUser: LiveData<PagingData<DataItem>> = _listUser

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUsers().cachedIn(viewModelScope).collect {
                _listUser.value = it
            }
        }
    }
}