package co.aladinjunior.instagram.splash.data

import com.google.firebase.auth.FirebaseAuth

class FireSplashRequest : SplashDataSource {

    override fun authenticate(callback: SplashCallback) {
        val uid = FirebaseAuth.getInstance().uid
        if (uid != null){
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}