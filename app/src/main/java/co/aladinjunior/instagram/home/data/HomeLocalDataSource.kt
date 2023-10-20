package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.IllegalStateException
import java.lang.RuntimeException

class HomeLocalDataSource(private val cache: Cache<List<Post>>) : HomeDataSource {

    override fun fetchPost(uuid: String, callback: BaseCallback<List<Post>>) {
        val posts = cache.get(uuid)
        if (posts != null){
            callback.onSuccess(posts)
        }
        else callback.onFailure("nao há posts no feed")
    }

    override fun fetchSession(): UserAuth {
        return Database.userSession ?: throw RuntimeException("usuário não logado")
    }

    override fun putPost(data: List<Post>?) {
        cache.put(data)
    }


}
