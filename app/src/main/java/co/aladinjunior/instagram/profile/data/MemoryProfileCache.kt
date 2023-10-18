package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.User

object MemoryProfileCache : Cache<Pair<User, Boolean?>> {
    private var userAuth: Pair<User, Boolean?>? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(response: String?): Pair<User, Boolean?>? {
        if (userAuth?.first?.uid == response){
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<User, Boolean?>?) {
        userAuth = data
    }
}