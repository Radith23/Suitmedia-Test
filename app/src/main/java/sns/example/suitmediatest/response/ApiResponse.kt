package sns.example.suitmediatest.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("avatar")
	val avatar: String,
)
