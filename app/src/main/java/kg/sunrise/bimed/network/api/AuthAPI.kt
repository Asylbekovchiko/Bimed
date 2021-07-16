package kg.sunrise.bimed.network.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @FormUrlEncoded
    // todo: Configure url
    @POST("account/auth")
    suspend fun enterWithPhoneNumber(
        @Field("phone") phoneNumber: String
    ): Response<ResponseBody>

    @FormUrlEncoded
    // todo: Configure url
    @POST("account/login-confirm/")
    suspend fun sendConfirmationCode(
        @Field("phone") phoneNumber: String,
        @Field("confirmation_code") code: String
    ): Response<ResponseBody>
}