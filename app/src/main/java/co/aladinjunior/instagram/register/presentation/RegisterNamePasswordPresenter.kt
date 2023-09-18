package co.aladinjunior.instagram.register.presentation

import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.register.RegisterNamePassword
import co.aladinjunior.instagram.register.data.RegisterCallback
import co.aladinjunior.instagram.register.data.RegisterRepository

class RegisterNamePasswordPresenter(var view: RegisterNamePassword.View? = null,

                                    val repository: RegisterRepository) :
    RegisterNamePassword.Presenter {


    override fun create(email: String, name: String, password: String, confirmPassword: String) {
        val isValidName = name.length > 3
        val isValidPassword = password.length >= 8
        val isValidConfirmPassword = confirmPassword == password

        if (!isValidName) view?.displayInvalidName(R.string.invalid_name)
        if (!isValidPassword) view?.displayInvalidPassword(R.string.invalid_password)
        if (!isValidConfirmPassword) view?.displayUnmatchPassword(R.string.password_not_equal)

        if (isValidName && isValidPassword && isValidConfirmPassword){
            view?.displayProgress(true)
            repository.registrate(email, name, password, object : RegisterCallback{
                override fun onSuccess() {
                    view?.goToWelcomeScreen()
                }

                override fun onFailure(message: String) {
                    view?.displayExistentUser(message)
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