package com.example.chatbot.api

import com.example.chatbot.model.MessageResponse
import com.example.chatbot.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrainShopApi {

    @GET("get")
    suspend fun getAnswer(
        @Query("bid") bid :String = Constants.BID,
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("uid") uid :String = "1",
        @Query("msg") msg :String
    ) :Response<MessageResponse>

}