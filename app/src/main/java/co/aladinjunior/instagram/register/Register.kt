package co.aladinjunior.instagram.register

import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface Register {

    interface Presenter : PresenterBase {

    }

    interface View : ViewBase<Presenter> {

    }
}