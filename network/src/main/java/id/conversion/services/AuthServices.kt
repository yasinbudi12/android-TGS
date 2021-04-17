package id.conversion.services

import id.conversion.request.LoginRequestBody
import id.conversion.request.PinCodeRequestBody
import id.conversion.request.RegisterRequestBody
import id.conversion.response.RegisterResponse
import id.conversion.response.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthServices {

    @GET("/mobile/user/check/phone/{phoneNumber}")
    suspend fun verifyPhoneNumber(@Path("phoneNumber") number: String): Response<Boolean>

    @POST("/mobile/user/check")
    suspend fun verifyPinCode(@Body requestBody: PinCodeRequestBody): Response<Boolean>

    @POST("/mobile/user/login")
    suspend fun login(@Body requestBody: LoginRequestBody): Response<Boolean>

    @POST("/mobile/user/register")
    suspend fun register(@Body requestBody: RegisterRequestBody): Response<RegisterResponse>

}