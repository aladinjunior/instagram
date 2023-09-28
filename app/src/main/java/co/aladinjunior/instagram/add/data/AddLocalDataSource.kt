package co.aladinjunior.instagram.add.data

import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.UserAuth
import java.lang.IllegalStateException

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return Database.userSession ?: throw IllegalStateException("usuario nao logado")
    }
}