package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.RuntimeException

interface ProfileDataSource {

    fun fetchUserProfile(userUuid: String, callback: BaseCallback<UserAuth>)
    fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Post>>)
    fun fetchUserSession() : UserAuth {throw RuntimeException()}
    fun putUser(data: UserAuth) {throw RuntimeException()}
    fun post(response: List<Post>?) {throw RuntimeException()}

}