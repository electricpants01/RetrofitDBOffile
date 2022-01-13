package com.locotodevteam.postdb1

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locotodevteam.postdb1.model.Post
import com.locotodevteam.postdb1.retrofit.RetrofitClient
import com.locotodevteam.postdb1.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    val myPosts = MutableLiveData<Post>()
    val TAG = MainActivityViewModel::class.simpleName
    val retrofit = RetrofitClient.getInstance()

    fun getPostById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val res = retrofit.create(RetrofitService::class.java).getPostById(id)
            if(res.isSuccessful){
                val body = res.body()
                body?.let {
                    Log.d(TAG, "getPostById: Todo salio muy bien")
                    myPosts.postValue(it)
                }
            }else{
                Log.d(TAG, "getPostById: Algo ocurrio mal")
            }
        }
    }

}