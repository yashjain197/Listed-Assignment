package com.example.listedassignment.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listedassignment.repository.DataRepository
import com.example.listedassignment.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(repository) as T
    }

}