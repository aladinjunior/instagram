package co.aladinjunior.instagram.commom.base

interface Cache<T> {

    fun isCached() : Boolean
    fun get(response: String?) : T?
    fun put(data: T)

}