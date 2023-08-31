package co.aladinjunior.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import co.aladinjunior.instagram.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val inputPasswordLogin = findViewById<TextInputEditText>(R.id.login_edit_text_password)
        val inputEmailLogin = findViewById<TextInputEditText>(R.id.login_edit_text_email)
        inputEmailLogin.addTextChangedListener(watcher)
        inputPasswordLogin.addTextChangedListener(watcher)

        val button = findViewById<LoadingButton>(R.id.login_bttn_enter)
            button.setOnClickListener {
                button.showProgress(true)

                findViewById<TextInputLayout>(R.id.login_input_email)
                    .error = "Este e-mail é inválido"
                findViewById<TextInputLayout>(R.id.login_input_password)
                    .error = "Esta senha é inválida"
                Handler(Looper.getMainLooper()).postDelayed({
                    button.showProgress(false)
                },2000)

            }




    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            findViewById<LoadingButton>(R.id.login_bttn_enter).isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }


}

