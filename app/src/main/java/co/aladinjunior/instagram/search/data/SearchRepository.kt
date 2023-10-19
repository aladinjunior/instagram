package co.aladinjunior.instagram.search.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth

class SearchRepository(private val dataSource: SearchDataSource) {

    fun fetchUsers(name: String, callback: BaseCallback<List<User>>){
        dataSource.fetchUsers(name, object : BaseCallback<List<User>>{
            override fun onSuccess(data: List<User>) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }
        })
    }

}