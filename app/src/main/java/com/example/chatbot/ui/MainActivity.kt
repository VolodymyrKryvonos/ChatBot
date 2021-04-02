package com.example.chatbot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbot.data.From
import com.example.chatbot.viewmodel.MainActivityViewModel
import com.example.chatbot.data.Message
import com.example.chatbot.databinding.ActivityMainBinding
import com.example.chatbot.repository.Repository
import com.example.chatbot.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var messagesAdapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainActivityViewModel::class.java)
            viewModel.getItems().observe(this, {
                messagesAdapter.notifyDataSetChanged()
                binding.messages.smoothScrollToPosition(
                    binding.messages.adapter!!.itemCount - 1)
            })

        binding = ActivityMainBinding.inflate(layoutInflater)

        messagesAdapter = MessageAdapter(viewModel.getItems().value!!)
        binding.messages.layoutManager = LinearLayoutManager(this)
        binding.messages.adapter = messagesAdapter
        val view = binding.root
        setContentView(view)
        binding.newMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    binding.send.visibility = View.GONE
                } else {
                    binding.send.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.send.setOnClickListener {
            val message = Message(binding.newMessage.text.toString(), From.Me)
            viewModel.addNewMessage(message)
            binding.newMessage.setText("")

        }
    }
}