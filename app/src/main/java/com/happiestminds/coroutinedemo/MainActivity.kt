package com.happiestminds.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var progressTextView: TextView
    lateinit var downloadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressTextView = findViewById(R.id.progressB)
        downloadButton = findViewById(R.id.downloadB)
    }

    fun downloadClick(view: View) {
        downloadButton.isEnabled = false
        progressTextView.text = "."
        val parentJ = CoroutineScope(Dispatchers.Default).launch {
            //TODO downloading 100 Images
            for (i in 0..9) {
                val j = CoroutineScope(Dispatchers.Default).launch {
                    // start one image download
                    delay(1000)
                }
                j.join()

                CoroutineScope(Dispatchers.Main).launch {
                    progressTextView.append(".")
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                downloadButton.isEnabled = true
            }
        }
    }
}