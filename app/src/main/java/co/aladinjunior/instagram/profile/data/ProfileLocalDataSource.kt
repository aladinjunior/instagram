package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.RuntimeException

class ProfileLocalDataSource(
    private val profileCache: ProfileCache<UserAuth>
) : ProfileDataSource{
    override fun fetchUserProfile(userUuid: String, callback: BaseCallback<UserAuth>) {

        val userAuth = profileCache.get(userUuid)
        if (userAuth != null){
            callback.onSuccess(userAuth)
        } else callback.onFailure("Usuário não encontrado")
    }

    override fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Photo>>) {
        //TODO: buscar posts
    }

    override fun fetchUserSession(): UserAuth {
        return Database.userSession ?: throw RuntimeException("usuário não logado")
    }

    override fun putUser(data: UserAuth) {
        profileCache.put(data)
    }
}