package co.aladinjunior.instagram.register

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView

interface RegisterPhotoUpload {

    interface View : BaseView<Presenter>{
        fun displayProgress(enabled: Boolean)
        fun onAttachFailure(message: String)
        fun onAttachSuccess()
    }
    interface Presenter : BasePresenter{
        fun attachPhotoToUser(uri: Uri)
    }
}