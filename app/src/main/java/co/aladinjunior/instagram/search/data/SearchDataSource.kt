package co.aladinjunior.instagram.search.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth

interface SearchDataSource {

    fun fetchUsers(name: String, callback: BaseCallback<List<User>>)
}