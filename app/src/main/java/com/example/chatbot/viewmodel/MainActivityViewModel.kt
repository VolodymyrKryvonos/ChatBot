package com.example.chatbot.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.data.From
import com.example.chatbot.data.Message
import com.example.chatbot.model.MessageResponse
import com.example.chatbot.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    private val _items: MutableLiveData<ArrayList<Message>> = MutableLiveData()
    val response: MutableLiveData<Response<MessageResponse>> = MutableLiveData()

    init {
        _items.value = arrayListOf()
        _items.value!!.add(Message("text", From.Bot))
    }
    fun getItems(): MutableLiveData<ArrayList<Message>> {
        return _items
    }

    fun addNewMessage(message: Message){
        _items.value?.add(message)
        _items.value = _items.value
        if (message.from== From.Me){
            request(message.messageText)
        }
    }

    private fun request(messageText: String) {
        viewModelScope.launch {
            val _response = repository.getAnswer("1", messageText)
            if (_response.isSuccessful){
                response.value = _response
                val msg = Message(_response.body()?.message!!, From.Bot)
                addNewMessage(msg)
            }else{
                Log.e("request", _response.errorBody().toString())
            }

        }
    }

}