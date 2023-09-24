package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.UserAuth

object MemoryProfileCache : Cache<UserAuth> {
    private var userAuth: UserAuth? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(response: String?): UserAuth? {
        if (userAuth?.uuid == response){
            return userAuth
        }
        return null
    }

    override fun put(data: UserAuth) {
        userAuth = data
    }
}