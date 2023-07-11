package com.example.listedassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listedassignment.models.DataList
import com.example.listedassignment.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MainViewModel(private val repository: DataRepository ): ViewModel() {

        fun getApiCall(){
            viewModelScope.launch(Dispatchers.IO){
                repository.getDataList()
            }
        }


    val data: LiveData<DataList>
    get() = repository.dataList

}