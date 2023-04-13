package com.vishal2376.treasurehint.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vishal2376.treasurehint.models.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val baseUrl="https://gdsc-treasure-hunt.vercel.app/"
//private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit=
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build()


interface NetworkApiService {
@POST("api/team/get")
suspend fun getUser(@Body loginData:LoginData): User?
@POST("api/team/all")
suspend fun getListUsers():LeaderBoard?
@PUT("api/team/useHint")
suspend fun useHint(@Body loginData:LoginData)
@Headers("Content-Type: application/json")
@POST("api/team/login")
suspend fun getLoginDetails(@Body loginData: LoginData):LoginDetails?


    companion object NetworkApi {
        val retrofitService: NetworkApiService by lazy {
            retrofit.create(NetworkApiService::class.java)
        }
    }
}
