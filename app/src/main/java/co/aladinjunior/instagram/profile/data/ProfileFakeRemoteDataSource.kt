package co.aladinjunior.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {

    override fun fetchUserProfile(userUuid: String, callback: BaseCallback<Pair<UserAuth, Boolean?>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userAuth.firstOrNull { userUuid == it.uuid }
            if (userAuth != null) {
                if (userAuth == Database.userSession){
                    callback.onSuccess(Pair(userAuth, null))
                } else {
                    val following = Database.followers[Database.userSession?.uuid]

                    val destUser = following?.firstOrNull{ it == userUuid}
                    callback.onSuccess(Pair(userAuth, destUser != null))

                }
            }
            else callback.onFailure("usuário não encontrado")
        }, 2000)


    }

    override fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.posts[userUuid]
            callback.onSuccess(posts?.toList() ?: emptyList())
        }, 2000)
    }

    override fun followUser(userUuid: String, isFollow: Boolean, callback: BaseCallback<Boolean>) {
        var followers = Database.followers[Database.userSession?.uuid]
        if(followers == null){
            followers = mutableSetOf()
            Database.followers[Database.userSession!!.uuid] = followers
        }
        if (isFollow){
            Database.followers[Database.userSession!!.uuid]?.add(userUuid)
        } else Database.followers[Database.userSession!!.uuid]?.remove(userUuid)

        callback.onSuccess(true)
    }

}