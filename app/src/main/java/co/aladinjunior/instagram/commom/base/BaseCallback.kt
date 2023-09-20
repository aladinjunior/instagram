package co.aladinjunior.instagram.commom.base

interface BaseCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}