package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.RuntimeException

interface ProfileDataSource {

    fun fetchUserProfile(userUuid: String, callback: BaseCallback<Pair<User, Boolean?>>)
    fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Post>>)
    fun followUser(userUuid: String, isFollow: Boolean,  callback: BaseCallback<Boolean>) {throw RuntimeException()}
    fun fetchUserSession() : String {throw RuntimeException()}
    fun putUser(data: Pair<User, Boolean?>) {throw RuntimeException()}
    fun post(response: List<Post>?) {throw RuntimeException()}

}