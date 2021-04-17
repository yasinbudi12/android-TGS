package id.conversion.services

import id.conversion.model.UserProfile
import id.conversion.request.UserProfileRequestBody
import id.conversion.response.Response
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserServices {

    @GET("/mobile/user/profile")
    suspend fun getUserProfile(@Header("token") accessToken: String): Response<UserProfile>

    @PUT("/mobile/user/update")
    suspend fun updateUserProfile(@Header("token") accessToken: String, @Body requestBody: UserProfileRequestBody): Response<Boolean>

    @Multipart
    @PUT("/mobile/avatar/update")
    suspend fun updateUserAvatar(@Header("token") accessToken: String, @Part file: MultipartBody.Part?): Response<Boolean>

}