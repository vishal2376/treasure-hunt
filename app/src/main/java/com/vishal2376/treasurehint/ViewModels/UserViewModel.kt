package com.vishal2376.treasurehint.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishal2376.treasurehint.models.User
import androidx.lifecycle.viewModelScope
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.LoginDetails
import com.vishal2376.treasurehint.network.NetworkApiService
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
   private val _loginDetails=MutableLiveData<LoginDetails>()
    val loginDetails:LiveData<LoginDetails> = _loginDetails
   private val _changeState=MutableLiveData<Boolean>()
    val changeState:LiveData<Boolean> = _changeState
     fun getUserData(loginData:LoginData) {

         viewModelScope.launch {
            try {
                _user.value = NetworkApiService.NetworkApi.retrofitService.getUser(
                    loginData
                    )

                Log.d("Network","${user.value}")

            } catch (e: Exception) {
                Log.d("Network","${e.message}")
            }
        }
    }
    fun getLoginDetails(loginData: LoginData){

        viewModelScope.launch {
            try {
                _loginDetails.value = NetworkApiService.NetworkApi.retrofitService.getLoginDetails(
                    loginData
                )

                Log.d("Network","${loginDetails.value?.message.toString()}")

            } catch (e: Exception) {

              Log.d("Network","${e.message}")
            }
        }
    }
}
