package com.example.postrequesthttp.ui.otherActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.postrequesthttp.R
import kotlinx.android.synthetic.main.activity_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondActivity : AppCompatActivity() {

    private val viewModel by viewModel<SecondViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setupViewModel()
        viewModel.loadUser()
    }

    private fun setupViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()

        })
        viewModel.data.observe(this, Observer {
            textbla.text = it.firstName.toString()

        })
    }
}