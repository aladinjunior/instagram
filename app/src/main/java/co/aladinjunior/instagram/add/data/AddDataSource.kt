package co.aladinjunior.instagram.add.data

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.UnsupportedOperationException

interface AddDataSource {

    fun createPost(uuid: String, uri: Uri, captions: String, callback: BaseCallback<Boolean>) {throw UnsupportedOperationException()}
    fun fetchSession() : UserAuth {throw UnsupportedOperationException()}
}