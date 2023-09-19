package co.aladinjunior.instagram.register

import android.net.Uri
import co.aladinjunior.instagram.commom.base.PresenterBase
import co.aladinjunior.instagram.commom.base.ViewBase

interface RegisterPhotoUpload {

    interface View : ViewBase<Presenter>{
        fun displayProgress(enabled: Boolean)
        fun onAttachFailure(message: String)
        fun onAttachSuccess()
    }
    interface Presenter : PresenterBase{
        fun attachPhotoToUser(uri: Uri)
    }
}