package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.UnsupportedOperationException
import java.util.*

interface HomeDataSource {
    fun fetchPost(uuid: String, callback: BaseCallback<List<Post>>)
    fun putPost(data: List<Post>?) {throw UnsupportedOperationException()}
    fun fetchSession() : UserAuth {throw UnsupportedOperationException()}
}