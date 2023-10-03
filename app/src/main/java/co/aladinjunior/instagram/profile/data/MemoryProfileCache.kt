package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.UserAuth

object MemoryProfileCache : Cache<Pair<UserAuth, Boolean?>> {
    private var userAuth: Pair<UserAuth, Boolean?>? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(response: String?): Pair<UserAuth, Boolean?>? {
        if (userAuth?.first?.uuid == response){
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth, Boolean?>?) {
        userAuth = data
    }
}