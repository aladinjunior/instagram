package co.aladinjunior.instagram.register.presentation

import android.net.Uri
import co.aladinjunior.instagram.register.RegisterPhotoUpload
import co.aladinjunior.instagram.register.data.RegisterCallback
import co.aladinjunior.instagram.register.data.RegisterRepository

class RegisterPhotoUploadPresenter(
    var view: RegisterPhotoUpload.View? = null,
    var repository: RegisterRepository
    ) : RegisterPhotoUpload.Presenter {

    override fun attachPhotoToUser(uri: Uri) {
        view?.displayProgress(true)
        repository.attachPhoto(uri, object : RegisterCallback{
            override fun onSuccess() {
                view?.onAttachSuccess()
            }

            override fun onFailure(message: String) {
               view?.onAttachFailure(message)
            }

            override fun onComplete() {
                view?.displayProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}