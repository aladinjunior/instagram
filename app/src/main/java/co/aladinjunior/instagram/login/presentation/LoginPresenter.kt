package co.aladinjunior.instagram.login.presentation

import android.util.Patterns
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.LoginCallback
import co.aladinjunior.instagram.login.data.LoginRepository


class LoginPresenter(
    private var view: Login.View?,
    private val repository: LoginRepository) : Login.Presenter{

    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8

        if (!isEmailValid) view?.displayInvalidEmail(R.string.invalid_email)
        else view?.displayInvalidEmail(null)

        if (!isPasswordValid) view?.displayInvalidPassword(R.string.invalid_password)
        else view?.displayInvalidPassword(null)

        if (isEmailValid && isPasswordValid){
            view?.displayProgress(true)
            repository.login(email, password, object : LoginCallback{
                override fun onSuccess() {
                    view?.authenticateUser()
                }

                override fun onFailure(message: String) {
                    view?.cantAuthenticateUser(message)
                }

                override fun onComplete() {
                    view?.displayProgress(false)
                }

            })
        }


    }

    override fun onDestroy() {
        view = null
    }

}