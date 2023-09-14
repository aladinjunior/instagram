package co.aladinjunior.instagram.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.aladinjunior.instagram.commom.util.CustomTextWatcher
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.ActivityLoginBinding
import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.FakeRequest
import co.aladinjunior.instagram.login.data.LoginRepository
import co.aladinjunior.instagram.login.presentation.LoginPresenter
import co.aladinjunior.instagram.main.view.MainActivity


class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        presenter = DependencyInjector.loginPresenter(this, DependencyInjector.loginRepository())





        with(binding) {
            loginEditTextEmail.addTextChangedListener(watcher)
            loginEditTextEmail.addTextChangedListener(CustomTextWatcher {
                displayInvalidEmail(null)
            })
            loginEditTextPassword.addTextChangedListener(watcher)
            loginEditTextPassword.addTextChangedListener(CustomTextWatcher {
                displayInvalidPassword(null)
            })


            loginBttnEnter.setOnClickListener {
                presenter.login(
                    loginEditTextEmail.text.toString(),
                    loginEditTextPassword.text.toString()
                )


            }
        }


    }


    private val watcher = CustomTextWatcher {
        binding.loginBttnEnter.isEnabled = binding.loginEditTextEmail.text.toString().isNotEmpty()
                && binding.loginEditTextPassword.text.toString().isNotEmpty()
    }

    override fun displayProgress(enabled: Boolean) {
        binding.loginBttnEnter.showProgress(enabled)
    }

    override fun displayInvalidEmail(message: Int?) {
        binding.loginInputEmail.error = message?.let { getString(message) }

    }

    override fun displayInvalidPassword(message: Int?) {
        binding.loginInputPassword.error = message?.let { getString(message) }


    }

    override fun authenticateUser() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun cantAuthenticateUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


}

