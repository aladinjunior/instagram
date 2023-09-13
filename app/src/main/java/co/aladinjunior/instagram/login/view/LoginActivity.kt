package co.aladinjunior.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import co.aladinjunior.instagram.custom.view.util.CustomWatcher
import co.aladinjunior.instagram.databinding.ActivityLoginBinding
import co.aladinjunior.instagram.login.Login


class LoginActivity : AppCompatActivity(), Login.View{

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        with(binding) {
            loginEditTextEmail.addTextChangedListener(watcher)
            loginEditTextPassword.addTextChangedListener(watcher)


            loginBttnEnter.setOnClickListener {
                


                loginInputPassword.error = "Esta senha é inválida"
                Handler(Looper.getMainLooper()).postDelayed({
                    loginBttnEnter.showProgress(false)
                }, 2000)

            }
        }


    }


    private val watcher = CustomWatcher{
       binding.loginBttnEnter.isEnabled = it.isNotEmpty()
    }

    override fun displayProgress(enabled: Boolean) {
        binding.loginBttnEnter.showProgress(enabled)
    }

    override fun displayInvalidEmail(message: Int) {
        binding.loginInputEmail.error = getString(message)
    }

    override fun displayInvalidPassword(message: Int) {
        binding.loginInputPassword.error = getString(message)
    }

    override fun authenticateUser() {
        //GO TO NEXT SCREEN
    }

    override fun cantAuthenticateUser(message: Int) {
        //DISPLAY USER NOT FOUND
    }


}

