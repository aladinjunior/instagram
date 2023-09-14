package co.aladinjunior.instagram.login

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface Login {

    interface View : ViewBase<Presenter>{

        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int?)
        fun displayInvalidPassword(@StringRes message: Int?)
        fun authenticateUser()
        fun cantAuthenticateUser(message: String)

    }

    interface Presenter : PresenterBase{

        fun login(email: String, password: String)

    }

}