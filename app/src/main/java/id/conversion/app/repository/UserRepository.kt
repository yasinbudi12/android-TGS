package id.conversion.app.repository

import id.conversion.app.shared.AppPreferences
import id.conversion.model.UserProfile
import id.conversion.request.UserProfileRequestBody
import id.conversion.response.Response
import id.conversion.services.UserServices
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDataSource: UserServices, private val appPreferences: AppPreferences) {

    suspend fun getUserProfile(): Response<UserProfile> {
        return remoteDataSource.getUserProfile(appPreferences.accessToken)
    }

    suspend fun updateUserProfile(requestBody: UserProfileRequestBody): Response<Boolean> {
        return remoteDataSource.updateUserProfile(appPreferences.accessToken, requestBody)
    }

    suspend fun updateUserAvatar(file: MultipartBody.Part?): Response<Boolean> {
        return remoteDataSource.updateUserAvatar(appPreferences.accessToken, file)
    }

}