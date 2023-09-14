package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface Register {

    interface Presenter : PresenterBase {


    }

    interface View : ViewBase<Presenter> {
        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int?)
        fun cadastrateEmail(email: String)

    }
}