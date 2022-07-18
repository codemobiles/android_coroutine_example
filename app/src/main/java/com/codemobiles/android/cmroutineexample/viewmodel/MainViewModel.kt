package com.codemobiles.android.cmroutineexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun run(){
        viewModelScope.launch{

        }
    }
}