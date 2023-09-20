package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface RegisterNamePassword {

    interface View : BaseView<Presenter>{
        fun displayProgress(enabled: Boolean)
        fun displayInvalidName(@StringRes message: Int?)
        fun displayInvalidPassword(@StringRes message: Int?)
        fun displayUnmatchPassword(@StringRes message: Int?)
        fun displayExistentUser(message: String)
        fun goToWelcomeScreen(name: String)
    }

    interface Presenter : BasePresenter{
        fun create(email: String, name: String, password: String, confirmPassword: String)
    }
}