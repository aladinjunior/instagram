package co.aladinjunior.instagram.register.presentation

import android.util.Patterns
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.data.RegisterEmailCallback
import co.aladinjunior.instagram.register.data.RegisterEmailRepository

class RegisterEmailPresenter(private var view: RegisterEmail.View?,
                             private var repository: RegisterEmailRepository) : RegisterEmail.Presenter {

    override fun registrate(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValidEmail) view?.displayInvalidEmail(R.string.invalid_email)

        else{
            view?.displayProgress(true)
            repository.registrate(email, object : RegisterEmailCallback{
                override fun onSuccess() {
                    view?.goToNamePasswordScreen()
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
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