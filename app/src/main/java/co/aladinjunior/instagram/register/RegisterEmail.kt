package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface RegisterEmail {

    interface Presenter : PresenterBase {
        fun registrate(email: String)

    }

    interface View : ViewBase<Presenter> {
        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int?)
        fun onEmailFailure(message: String)
        fun goToNamePasswordScreen(email: String)


    }
}