package com.example.listedassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listedassignment.api.ApiService
import com.example.listedassignment.models.Data
import com.example.listedassignment.models.DataList

class DataRepository(private val apiService: ApiService) {

    private val dataListLiveData = MutableLiveData<DataList>()

    val dataList: LiveData<DataList>
    get() = dataListLiveData


    suspend fun getDataList(){
        val authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlh" +
                "dCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
        val result = apiService.getDataList(authToken)
        if(result?.body() != null){
            dataListLiveData.postValue(result.body())
        }
    }
}