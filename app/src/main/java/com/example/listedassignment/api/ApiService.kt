package com.example.listedassignment.api

import com.example.listedassignment.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("dashboardNew")
    suspend fun getDataList(@Header("Authorization") authToken:String): Response<DataList>
}