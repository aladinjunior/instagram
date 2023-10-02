package co.aladinjunior.instagram.search

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView
import co.aladinjunior.instagram.commom.model.UserAuth

interface Search {

    interface Presenter : BasePresenter{
        fun fetchUsers(name: String)
    }
    interface View: BaseView<Presenter>{
        fun displayUsers(user: List<UserAuth>)
        fun displayEmptyUsers(message: String)
    }
}