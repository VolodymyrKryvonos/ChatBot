package com.example.chatbot.model

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("cnt")
    var message: String
)
