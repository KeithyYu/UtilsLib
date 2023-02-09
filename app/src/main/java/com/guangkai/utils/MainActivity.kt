package com.guangkai.utils

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.TextView
import com.guangkai.handlerlibrary.WeakHandler

class MainActivity : AppCompatActivity() {
    companion object {
    }
    private val TAG = MainActivity::class.simpleName

    private val mHandler by lazy { MyHandler(this) }

    private lateinit var mTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView = findViewById(R.id.hello_world)
        mHandler.sendEmptyMessageDelayed(0, 4000)
    }

    class MyHandler(host : MainActivity) : WeakHandler<MainActivity>(host) {
        override fun handleMessageWhenServive(msg: Message, host: MainActivity) {
            when(msg.what) {
                0 -> {
                    Log.d(host.TAG, "handleMessageWhenServive")
                    host.mTextView.text = "handleMessageWhenServive"
                }
            }
        }

        override fun handleMessageWhenNotServive(msg: Message) {
            super.handleMessageWhenNotServive(msg)
        }
    }
}