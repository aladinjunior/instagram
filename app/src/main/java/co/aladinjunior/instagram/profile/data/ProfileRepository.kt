package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun clear(){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.post(null)
    }

    fun fetchUserProfile(uuid: String?, callback: BaseCallback<Pair<UserAuth, Boolean?>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchUserSession().uuid
        val dataSource = dataSourceFactory.createFromUser(uuid)

        dataSource.fetchUserProfile(userID, object : BaseCallback<Pair<UserAuth, Boolean?>>{
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                if (uuid == null){
                    localDataSource.putUser(data)

                }

                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })
    }

    fun fetchUserPosts(uuid: String?, callback: BaseCallback<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchUserSession().uuid
        val dataSource = dataSourceFactory.createFromPosts(uuid)
        dataSource.fetchUserPosts(userID, object : BaseCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if (uuid == null){
                    localDataSource.post(data)
                }

                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })
    }

    fun followUser(uuid: String?, following: Boolean, callback: BaseCallback<Boolean>){
        val dataSource = dataSourceFactory.createRemoteDataSource()
        val userID = uuid ?: dataSource.fetchUserSession().uuid

        dataSource.followUser(userID, following, object : BaseCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })

    }
}