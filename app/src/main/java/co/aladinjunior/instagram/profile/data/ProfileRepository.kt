package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileRepository(private val dataSource: ProfileDataSource) {

    fun fetchUserProfile(userUuid: String, callback: BaseCallback<UserAuth>){
        dataSource.fetchUserProfile(userUuid, callback)
    }

    fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Photo>>){
        dataSource.fetchUserPosts(userUuid, callback)
    }
}