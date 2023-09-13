package co.aladinjunior.instagram.login.presentation

import android.util.Patterns
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.login.Login


class LoginPresenter(private var view: Login.View?) : Login.Presenter{

    override fun login(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) view?.displayInvalidEmail(R.string.invalid_email)
        else view?.displayInvalidEmail(null)

        if (password.length < 8) view?.displayInvalidPassword(R.string.invalid_password)
        else view?.displayInvalidPassword(null)


    }

    override fun onDestroy() {
        view = null
    }

}