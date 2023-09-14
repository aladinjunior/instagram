package co.aladinjunior.instagram.login.data

import android.os.Handler
import android.os.Looper


class FakeRequest : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {

        if (email == "a@a.com" && password == "12345678"){
            callback.onSuccess()
            callback.onComplete()
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                callback.onFailure("usuário não encontrado")
                callback.onComplete()
            }, 2000)
        }
    }
}