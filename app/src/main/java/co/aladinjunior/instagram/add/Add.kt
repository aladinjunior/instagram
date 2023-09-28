package co.aladinjunior.instagram.add

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface Add {
    interface View: BaseView<Presenter> {
        fun onSuccesPost()
        fun postingFailure(message: String)
    }
    interface Presenter : BasePresenter{
        fun createPost(uri: Uri, captions: String)
    }
}