package com.example.singup_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class Create_notes : AppCompatActivity() {
    private  var headerEditText: AppCompatEditText? = null
    private  var dateEditText: AppCompatEditText? = null
    private  var messageEditText: AppCompatEditText? = null
    private  var createButton: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notes)


        headerEditText = findViewById(R.id.header_cr_n)
        dateEditText = findViewById(R.id.date_cr_n)
        messageEditText = findViewById(R.id.message_cr_n)
        createButton = findViewById(R.id.create)

        createButton?.setOnClickListener {

            val header = headerEditText?.text.toString()
            val date = dateEditText?.text.toString()
            val message = messageEditText?.text.toString()


            val intent = Intent().apply {
                putExtra("header", header)
                putExtra("date", date)
                putExtra("message", message)
            }


            setResult(RESULT_OK, intent)
            finish()
        }
    }
}