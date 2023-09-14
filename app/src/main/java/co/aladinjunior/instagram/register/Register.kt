package co.aladinjunior.instagram.register

import androidx.annotation.StringRes
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface Register {

    interface Presenter : PresenterBase {
        fun registrate(email: String)

    }

    interface View : ViewBase<Presenter> {
        fun displayInvalidEmail(@StringRes message: Int?)


    }
}