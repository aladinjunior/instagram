package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface RegisterNamePassword {

    interface View : ViewBase<Presenter>{
        fun displayProgress(enabled: Boolean)
        fun displayInvalidName(@StringRes message: Int?)
        fun displayInvalidPassword(@StringRes message: Int?)
        fun displayUnmatchPassword(@StringRes message: Int?)
        fun displayExistentUser(message: String)
        fun goToWelcomeScreen()
    }

    interface Presenter : PresenterBase{
        fun create(email: String, name: String, password: String, confirmPassword: String)
    }
}