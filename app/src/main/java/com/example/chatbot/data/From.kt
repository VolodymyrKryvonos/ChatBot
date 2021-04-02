package com.example.chatbot.data

import com.example.chatbot.R

sealed class From(val shapeDrawableId :Int) {
    object Bot : From(R.drawable.bot_message_shape){
    }
    object Me : From(R.drawable.your_message_shape){
    }
}