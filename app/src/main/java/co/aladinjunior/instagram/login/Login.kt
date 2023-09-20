package co.aladinjunior.instagram.login

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface Login {

    interface View : BaseView<Presenter>{

        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int?)
        fun displayInvalidPassword(@StringRes message: Int?)
        fun authenticateUser()
        fun cantAuthenticateUser(message: String)

    }

    interface Presenter : BasePresenter{

        fun login(email: String, password: String)

    }

}