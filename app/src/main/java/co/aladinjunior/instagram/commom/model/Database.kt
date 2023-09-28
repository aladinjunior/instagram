package co.aladinjunior.instagram.commom.model

import java.util.*

object Database {
    val userAuth = hashSetOf<UserAuth>()
    val photo = hashSetOf<Photo>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()
    var userSession: UserAuth? = null



    init {

        with(userAuth){
            val userA = UserAuth(UUID.randomUUID().toString(),"userA", "userA@gmail.com", "12345678")
            val userB = UserAuth(UUID.randomUUID().toString(),"userB", "userB@gmail.com", "12345678")

            add(userA)
            add(userB)

            followers[userA.uuid] = hashSetOf()
            posts[userA.uuid] = hashSetOf()
            feeds[userA.uuid] = hashSetOf()

            followers[userB.uuid] = hashSetOf()
            posts[userB.uuid] = hashSetOf()
            feeds[userB.uuid] = hashSetOf()

            userSession = userAuth.first()
        }



    }

}