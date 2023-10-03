package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.IllegalStateException
import java.lang.RuntimeException

class ProfileLocalDataSource(
    private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
    private val postsProfileCache: Cache<List<Post>>
) : ProfileDataSource{
    override fun fetchUserProfile(userUuid: String, callback: BaseCallback<Pair<UserAuth, Boolean?>>) {

        val userAuth = profileCache.get(userUuid)
        if (userAuth != null){
            callback.onSuccess(userAuth)
        } else callback.onFailure("Usuário não encontrado")
    }

    override fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Post>>) {
       var posts = postsProfileCache.get(userUuid)
        if(posts != null){
            posts = Database.posts[userUuid]?.toList() ?: emptyList()
            callback.onSuccess(posts)
        }
        else{
            callback.onFailure("usuário não postou nada")
        }
    }

    override fun fetchUserSession(): UserAuth {
        return Database.userSession ?: throw RuntimeException("usuário não logado")
    }

    override fun putUser(data: Pair<UserAuth, Boolean?>) {
        profileCache.put(data)
    }

    override fun post(response: List<Post>?) {
        postsProfileCache.put(response)
    }
}