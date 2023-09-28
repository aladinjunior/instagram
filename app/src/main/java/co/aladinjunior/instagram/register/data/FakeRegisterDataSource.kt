package co.aladinjunior.instagram.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.UserAuth
import java.util.*

class FakeRegisterDataSource : RegisterDataSource{

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
                val newSession =  UserAuth(UUID.randomUUID().toString(), name,null, email, password)
                val created = Database.userAuth.add(newSession)
                if (created){
                    Database.userSession = newSession
                    callback.onSuccess()
                } else callback.onFailure("erro interno")
            } else callback.onFailure("usuário já cadastrado")


            callback.onComplete()
        },2000)
    }

    override fun attachPhoto(uri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userSession
            if (userAuth == null){
                callback.onFailure("usuário não encontrado!")
            } else {
               val index = Database.userAuth.indexOf(Database.userSession)
                Database.userAuth[index] = Database.userSession!!.copy(photoUri = uri)
                Database.userSession = Database.userAuth[index]
                callback.onSuccess()
            }

            callback.onComplete()
        },2000)
    }
}