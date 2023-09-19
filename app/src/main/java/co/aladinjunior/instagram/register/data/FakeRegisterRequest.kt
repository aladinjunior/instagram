package co.aladinjunior.instagram.register.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.UserAuth
import java.util.*

class FakeRegisterRequest : RegisterDataSource{

    override fun registrate(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userAuth.firstOrNull { email == it.email }
            if (userAuth == null) callback.onSuccess()
            else callback.onFailure("usuário já cadastrado")

            callback.onComplete()
        },2000)
    }

    override fun registrate(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userAuth.firstOrNull { email == it.email }
            if(userAuth == null) {
                val newSession =  UserAuth(UUID.randomUUID().toString(), name, email, password)
                val created = Database.userAuth.add(newSession)
                if (created){
                    Database.userSession = newSession
                    callback.onSuccess()
                } else callback.onFailure("erro interno")
            } else callback.onFailure("usuário já cadastrado")


            callback.onComplete()
        },2000)
    }
}