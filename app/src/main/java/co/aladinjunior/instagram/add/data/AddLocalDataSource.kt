package co.aladinjunior.instagram.add.data

import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.UserAuth
import com.google.firebase.auth.FirebaseAuth
import java.lang.IllegalStateException
import java.lang.RuntimeException

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("usuario nao logado")
    }
}