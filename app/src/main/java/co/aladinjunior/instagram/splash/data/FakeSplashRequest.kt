package co.aladinjunior.instagram.splash.data

import co.aladinjunior.instagram.commom.model.Database

class FakeSplashRequest : SplashDataSource {
    override fun authenticate(callback: SplashCallback) {
        val userAuth = Database.userSession
        if (userAuth != null){
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}
