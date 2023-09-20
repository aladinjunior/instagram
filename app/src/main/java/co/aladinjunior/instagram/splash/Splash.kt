package co.aladinjunior.instagram.splash

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface Splash {

    interface View: BaseView<Presenter>{
        fun onAuthSuccess()
        fun onAuthFailure()
    }
    interface Presenter: BasePresenter{
        fun authenticate()
    }
}