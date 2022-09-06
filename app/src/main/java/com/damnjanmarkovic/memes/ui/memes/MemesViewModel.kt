package com.damnjanmarkovic.memes.ui.memes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damnjanmarkovic.memes.helpers.ScreenState
import com.damnjanmarkovic.memes.network.ApiClient
import com.damnjanmarkovic.memes.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.damnjanmarkovic.memes.models.Meme
import com.damnjanmarkovic.memes.models.MemeResponse

class MemesViewModel(private val repository:
                     Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private var _charactersLiveData = MutableLiveData<ScreenState<List<Meme?>?>>()

    val characterLivedata: LiveData<ScreenState<List<Meme?>?>>
        get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter(){
        val client = repository.getCharacters("1")
        _charactersLiveData.postValue(ScreenState.Loading(null))

        client.enqueue(object : Callback<MemeResponse> {
            override fun onResponse(
                call: Call<MemeResponse>,
                response: Response<MemeResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("characters", response.body().toString())
                    _charactersLiveData.postValue(ScreenState.Success(response.body()?.data?.memes))
                }
                else {
                    _charactersLiveData.postValue(ScreenState.Error(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<MemeResponse>, t: Throwable) {
                Log.e("failed", t.message.toString())
                _charactersLiveData.postValue(ScreenState.Error(t.message.toString()))
            }

        })
    }
}