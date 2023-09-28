package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {

    fun clearCache(){
        val localDataSource = dataSourceFactory.createLocalDataSoure()
        localDataSource.putPost(null)
    }

    fun fetchPosts(callback: BaseCallback<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSoure()
        val userAuth = localDataSource.fetchSession()
        val dataSource = dataSourceFactory.createFromPosts()
        dataSource.fetchPost(userAuth.uuid, object : BaseCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPost(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })


    }
}