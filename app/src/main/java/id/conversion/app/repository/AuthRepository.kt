package id.conversion.app.repository

import id.conversion.app.BuildConfig
import id.conversion.app.shared.AppPreferences
import id.conversion.database.UserDatabase
import id.conversion.entity.UserEntity
import id.conversion.model.UserProfile
import id.conversion.request.LoginRequestBody
import id.conversion.request.PinCodeRequestBody
import id.conversion.request.RegisterRequestBody
import id.conversion.response.RegisterResponse
import id.conversion.response.Response
import id.conversion.services.AuthServices
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthServices,
    private val localDataSource: UserDatabase,
    private val appPreferences: AppPreferences
) {

    fun storeAccessToken(token: String) {
        appPreferences.accessToken = token
    }

    suspend fun verifyPhoneNumber(phoneNumber: String): Response<Boolean> {
        return remoteDataSource.verifyPhoneNumber(phoneNumber)
    }

    suspend fun verifyPinCode(phoneNumber: String, pinCode: String): Response<Boolean> {
        return remoteDataSource.verifyPinCode(PinCodeRequestBody(phoneNumber, pinCode))
    }

    suspend fun login(request: LoginRequestBody): Response<Boolean> {
        return remoteDataSource.login(request)
    }

    suspend fun register(request: RegisterRequestBody): Response<RegisterResponse> {
        return remoteDataSource.register(request)
    }

    suspend fun getUserEntity(): UserEntity? {
        return localDataSource.dao().getUser()
    }

    suspend fun insertUser(number: String) {
        localDataSource.dao().insert(UserEntity(phoneNumber = number))
    }

    suspend fun updateUserProfile(user: UserProfile): Int {
        val entity = localDataSource.dao().getUser().also {
            it?.profileId = user.id
            it?.email = user.email
            it?.fullName = "${user.firstName} ${user.lastName}"
            it?.avatarUrl = "${BuildConfig.URL_AVATAR}${user.id}"
        }

        return if (entity != null) localDataSource.dao().update(entity) else 0
    }

    suspend fun updateUserBalance(nominal: Int, deduct: Boolean): Int {
        val entity = localDataSource.dao().getUser()?.also {
            if (deduct) it.balance -= nominal else it.balance += nominal
        }

        return if (entity != null) localDataSource.dao().update(entity) else 0
    }

    suspend fun removeUser(entity: UserEntity): Int {
        return localDataSource.dao().delete(entity)
    }

}