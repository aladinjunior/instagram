package co.aladinjunior.instagram.profile.data

interface ProfileCache<T> {

    fun isCached() : Boolean
    fun get(response: String?) : T?
    fun put(data: T)

}