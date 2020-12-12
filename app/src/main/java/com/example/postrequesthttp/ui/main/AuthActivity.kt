package com.example.postrequesthttp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.postrequesthttp.R
import com.example.postrequesthttp.ui.otherActivity.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthViewModel>()

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       setupListeners()
       setupViewModel()
   }



    private fun setupListeners() {
        btn.setOnClickListener {
            viewModel.login(email.text.toString(),
                password.text.toString())
        }
    }

    private fun setupViewModel() {
        viewModel.actionNewScreen.observe(this, Observer {
           startActivity(Intent(this, SecondActivity::class.java))
        })

        viewModel.error.observe(this, Observer {
               Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })
    }
}