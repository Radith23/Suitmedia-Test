package sns.example.suitmediatest.network

import retrofit2.http.GET
import retrofit2.http.Query
import sns.example.suitmediatest.response.ApiResponse

interface ApiService {
    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResponse
}