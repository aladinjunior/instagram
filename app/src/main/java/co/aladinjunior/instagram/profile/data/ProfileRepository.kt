package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun clear(){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.post(null)
    }

    fun fetchUserProfile(uuid: String?, callback: BaseCallback<UserAuth>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = uuid ?: localDataSource.fetchUserSession().uuid
        val dataSource = dataSourceFactory.createFromUser(uuid)

        dataSource.fetchUserProfile(userID, object : BaseCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
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
}