package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.UserAuth

interface ProfileDataSource {

    fun fetchUserProfile(userUuid: String, callback: BaseCallback<UserAuth>)
    fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Photo>>)
}