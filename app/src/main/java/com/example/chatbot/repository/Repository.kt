package com.example.chatbot.repository

import com.example.chatbot.api.RetrofitInstance
import com.example.chatbot.model.MessageResponse
import retrofit2.Response

class Repository {

    suspend fun getAnswer(userId: String, msg: String): Response<MessageResponse>{
        return RetrofitInstance.api.getAnswer(uid = userId, msg = msg)
    }

}