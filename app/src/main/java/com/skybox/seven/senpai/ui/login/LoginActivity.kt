package com.skybox.seven.senpai.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skybox.seven.senpai.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        submit_login.setOnClickListener {
            viewModel.login(username.text.toString(), password.text.toString())
        }

        viewModel.loginDetails.observe(this, Observer {
            if (it)
                Toast.makeText(this, "You have been logged in", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Failed to log in", Toast.LENGTH_SHORT).show()
        })
    }
}