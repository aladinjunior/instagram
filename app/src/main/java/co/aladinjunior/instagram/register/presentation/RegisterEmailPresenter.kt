package co.aladinjunior.instagram.register.presentation

import android.util.Patterns
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.data.RegisterCallback
import co.aladinjunior.instagram.register.data.RegisterRepository

class RegisterEmailPresenter(private var view: RegisterEmail.View?,
                             private val repository: RegisterRepository) : RegisterEmail.Presenter {

    override fun registrate(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValidEmail) view?.displayInvalidEmail(R.string.invalid_email)

        else{
            view?.displayProgress(true)
            repository.registrate(email, object : RegisterCallback{
                override fun onSuccess() {
                    view?.goToNamePasswordScreen(email)
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