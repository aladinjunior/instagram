package co.aladinjunior.instagram.splash

import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface Splash {

    interface View: ViewBase<Presenter>{
        fun onAuthSuccess()
        fun onAuthFailure()
    }
    interface Presenter: PresenterBase{
        fun authenticate()
    }
}