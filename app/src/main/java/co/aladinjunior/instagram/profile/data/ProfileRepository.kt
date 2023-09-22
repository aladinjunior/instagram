package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun fetchUserProfile(callback: BaseCallback<UserAuth>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchUserSession()
        val dataSource = dataSourceFactory.createFromUser()
        dataSource.fetchUserProfile(userAuth.uuid, object : BaseCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                localDataSource.putUser(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })
    }

    fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Photo>>){
        //TODO
    }
}