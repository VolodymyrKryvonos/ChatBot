package com.example.chatbot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.data.From
import com.example.chatbot.data.Message
import com.example.chatbot.databinding.MsgItemBinding

class MessageAdapter(private val messages: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MsgItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.binding.message.text = messages[position].messageText
        when (messages[position].from)  {
            From.Bot -> {
                val layoutParams =
                    holder.binding.message.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.horizontalBias = 0F
                holder.binding.message.setBackgroundResource(From.Bot.shapeDrawableId)
            }
            From.Me -> {
                val layoutParams =
                    holder.binding.message.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.horizontalBias = 1F
                holder.binding.message.setBackgroundResource(From.Me.shapeDrawableId)
            }
        }
    }

    override fun getItemCount() = messages.size
    inner class MessageViewHolder(val binding: MsgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}

