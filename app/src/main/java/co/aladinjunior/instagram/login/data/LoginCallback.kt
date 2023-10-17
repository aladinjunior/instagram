package co.aladinjunior.instagram.login.data

import co.aladinjunior.instagram.commom.model.UserAuth

interface LoginCallback {

    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()

}