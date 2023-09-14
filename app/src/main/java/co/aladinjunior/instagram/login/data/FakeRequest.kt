package co.aladinjunior.instagram.login.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.model.Database


class FakeRequest : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {

        Handler(Looper.getMainLooper()).postDelayed({

            //Get a user from fake database if exists and return null if not
            //SELECT * FROM USERAUTH WHERE EMAIL == EMAIL LIMIT 1
            val userAuth = Database.userAuth.firstOrNull { email == it.email }

            when {
                userAuth == null -> callback.onFailure("usuário não encontrado")
                userAuth.password != password -> callback.onFailure("senha inválida")
                else -> {
                    callback.onSuccess(userAuth)
                    Database.userSession = userAuth
                }
            }

            callback.onComplete()
        }, 2000)


    }
}