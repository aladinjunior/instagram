package co.aladinjunior.instagram.register.presentation

import android.util.Patterns
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.register.Register
import java.util.regex.Pattern

class RegisterPresenter(private var view: Register.View?) : Register.Presenter {

    override fun registrate(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValidEmail) view?.displayInvalidEmail(R.string.invalid_email)


    }

    override fun onDestroy() {
        view = null
    }
}