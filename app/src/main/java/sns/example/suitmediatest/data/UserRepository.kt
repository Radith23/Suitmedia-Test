package sns.example.suitmediatest.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import sns.example.suitmediatest.network.ApiService
import sns.example.suitmediatest.response.DataItem

class UserRepository private constructor(private val apiService: ApiService) {

    fun getUsers(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).flow
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}