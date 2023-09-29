package co.aladinjunior.instagram.post

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface Post {

    interface Presenter : BasePresenter{
        fun fetchPics()

    }
    interface View: BaseView<Presenter>{
        fun showMessageOnScreen(message: String)
        fun loadAllPics(pictures: List<Uri>)
    }
}