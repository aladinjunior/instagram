package co.aladinjunior.instagram.search

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface Search {

    interface Presenter : BasePresenter
    interface View: BaseView<Presenter>
}