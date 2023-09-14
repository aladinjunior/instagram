package co.aladinjunior.instagram.commom.model

import java.util.*

object Database {
    val userAuth = hashSetOf<UserAuth>()
    var userSession: UserAuth? = null


    init {

        with(userAuth){
            add(UserAuth(UUID.randomUUID().toString(), "userA@gmail.com", "12345678"))
            add(UserAuth(UUID.randomUUID().toString(), "userB@gmail.com", "87654321"))
        }


    }

}