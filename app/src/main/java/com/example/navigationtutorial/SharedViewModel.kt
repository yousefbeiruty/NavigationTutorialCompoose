package com.example.navigationtutorial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel  @Inject constructor():ViewModel() {

    var flag by mutableStateOf<Boolean>(true)
        private set
    fun visibleBottom(visible: Boolean){
        flag=visible
    }
}