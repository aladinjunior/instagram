package co.aladinjunior.instagram.add.presentation

import android.net.Uri
import co.aladinjunior.instagram.add.Add
import co.aladinjunior.instagram.add.data.AddRepository
import co.aladinjunior.instagram.commom.base.BaseCallback

class AddPresenter(
    var view: Add.View?,
    private val repository: AddRepository
) : Add.Presenter {

    override fun createPost(uri: Uri, captions: String) {
        repository.createPost(uri, captions, object : BaseCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                if (data) view?.onSuccesPost()
                else view?.postingFailure("erro interno")
            }

            override fun onFailure(message: String) {
                view?.postingFailure(message)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}