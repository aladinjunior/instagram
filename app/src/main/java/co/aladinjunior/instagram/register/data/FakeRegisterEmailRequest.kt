package co.aladinjunior.instagram.register.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.model.Database

class FakeRegisterEmailRequest : RegisterEmailDataSource{

    override fun registrate(email: String, callback: RegisterEmailCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userAuth.firstOrNull { email == it.email }
            if (userAuth == null) callback.onSuccess()
            else callback.onFailure("usuário já cadastrado")

            callback.onComplete()
        },2000)
    }
}