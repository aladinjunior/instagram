package co.aladinjunior.instagram.add.data

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BaseCallback

class AddRepository(private val remoteDataSource: AddFireRemoteDataSource,
                    private val localDataSource: AddLocalDataSource) {

    fun createPost(uri: Uri, captions: String, callback: BaseCallback<Boolean>){
        val userAuth = localDataSource.fetchSession()


        remoteDataSource.createPost(userAuth, uri, captions, object : BaseCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })
    }
}