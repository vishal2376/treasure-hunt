package com.vishal2376.treasurehint.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishal2376.treasurehint.models.User
import androidx.lifecycle.viewModelScope
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.network.NetworkApiService
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            try {
                _user.value = NetworkApiService.NetworkApi.retrofitService.getUser(
                    LoginData(
                        "tokyo11422@treasurehunt.gdsc",
                        "women00@"
                    )
                )
                Log.d("Network","${user.value}")
            } catch (e: Exception) {
                Log.d("Network", "${e.message}")
            }
        }
    }
}
