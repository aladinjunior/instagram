package co.aladinjunior.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import co.aladinjunior.instagram.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        with(binding) {
            loginEditTextEmail.addTextChangedListener(watcher)
            loginEditTextPassword.addTextChangedListener(watcher)


            loginBttnEnter.setOnClickListener {
                loginBttnEnter.showProgress(true)

                loginInputEmail.error = "Este e-mail é inválido"
                loginInputPassword.error = "Esta senha é inválida"
                Handler(Looper.getMainLooper()).postDelayed({
                    loginBttnEnter.showProgress(false)
                }, 2000)

            }
        }


    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.loginBttnEnter.isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }


}

