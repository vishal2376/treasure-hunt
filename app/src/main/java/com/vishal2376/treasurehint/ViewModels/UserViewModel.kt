package com.vishal2376.treasurehint.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishal2376.treasurehint.models.User
import androidx.lifecycle.viewModelScope
import com.vishal2376.treasurehint.models.LeaderBoard
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.LoginDetails
import com.vishal2376.treasurehint.network.NetworkApiService
import kotlinx.coroutines.launch
enum class ApiStatus{
    LOADING,ERROR,SUCCESS
}
class UserViewModel:ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
   private val _loginDetails=MutableLiveData<LoginDetails>()
    val loginDetails:LiveData<LoginDetails> = _loginDetails
   private val _leaderBoard=MutableLiveData<LeaderBoard>()
    val leaderBoard:LiveData<LeaderBoard> = _leaderBoard
    val _loginStatus=MutableLiveData<ApiStatus>()
    val loginStatus:LiveData<ApiStatus> = _loginStatus
    val _allStatus= MutableLiveData<ApiStatus>()
    val allStatus:LiveData<ApiStatus> = _allStatus
    val _userStatus= MutableLiveData<ApiStatus>()
    val userStatus:LiveData<ApiStatus> = _userStatus
    val _combineStatus=MutableLiveData<Pair<ApiStatus?,ApiStatus?>>()
    val combineStatus:LiveData<Pair<ApiStatus?,ApiStatus?>> = _combineStatus
     fun getUserData(loginData:LoginData) {

         viewModelScope.launch {
            _userStatus.value=ApiStatus.LOADING
             try {
                _user.value = NetworkApiService.NetworkApi.retrofitService.getUser(
                    loginData
                    )
                _userStatus.value=ApiStatus.SUCCESS
                Log.d("Network","${user.value}")

            } catch (e: Exception) {
                _userStatus.value=ApiStatus.ERROR
                Log.d("Network","${e.message}")
            }
        }
    }
    fun getLoginDetails(loginData: LoginData){
        _combineStatus.value=Pair(loginStatus.value
        ,(userStatus.value)
        )
        viewModelScope.launch {
           _loginStatus.value=ApiStatus.LOADING
            try {
                _loginDetails.value = NetworkApiService.NetworkApi.retrofitService.getLoginDetails(
                    loginData
                )
                _loginStatus.value=ApiStatus.SUCCESS
                Log.d("Network","${loginDetails.value?.message.toString()}")

            } catch (e: Exception) {
              _loginStatus.value=ApiStatus.ERROR
              Log.d("Network","${e.message}")
            }
        }
    }
    fun getLeaderBoard()
    {


        viewModelScope.launch {
            _allStatus.value=ApiStatus.LOADING
            try{
                _leaderBoard.value=NetworkApiService.NetworkApi.retrofitService.getListUsers()
                _allStatus.value=ApiStatus.SUCCESS
                Log.d("Network","asdasdsad ${leaderBoard.value}")
            }
            catch (e:Exception){
                _allStatus.value=ApiStatus.ERROR
                Log.d("Network","asdsadasd ${e.message}")
            }
        }
    }
}
