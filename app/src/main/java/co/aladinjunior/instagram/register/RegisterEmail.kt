package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter {
        fun registrate(email: String)

    }

    interface View : BaseView<Presenter> {
        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int?)
        fun onEmailFailure(message: String)
        fun goToNamePasswordScreen(email: String)


    }
}