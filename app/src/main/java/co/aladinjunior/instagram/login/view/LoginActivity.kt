package co.aladinjunior.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.aladinjunior.instagram.custom.util.CustomTextWatcher
import co.aladinjunior.instagram.databinding.ActivityLoginBinding
import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.presentation.LoginPresenter


class LoginActivity : AppCompatActivity(), Login.View{

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)


        with(binding) {
            loginEditTextEmail.addTextChangedListener(watcher)
            loginEditTextEmail.addTextChangedListener(CustomTextWatcher{
               displayInvalidEmail(null)
            })
            loginEditTextPassword.addTextChangedListener(watcher)
            loginEditTextPassword.addTextChangedListener(CustomTextWatcher{
                displayInvalidPassword(null)
            })


            loginBttnEnter.setOnClickListener {
                    presenter.login(loginEditTextEmail.text.toString(), loginEditTextPassword.text.toString())


            }
        }


    }


    private val watcher = CustomTextWatcher{
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
        //GO TO NEXT SCREEN
    }

    override fun cantAuthenticateUser(message: Int) {
        //DISPLAY USER NOT FOUND
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


}

