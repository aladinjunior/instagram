package co.aladinjunior.instagram.commom.model

import android.net.Uri
import java.io.File
import java.util.*

object Database {
    val userAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, MutableSet<String>>()
    var userSession: UserAuth? = null

    private const val FILE_PATHNAME = "/sdcard/Pictures/IMG_20230930_102025.jpg"



    init {

        with(userAuth){
            val userA = UserAuth(UUID.randomUUID().toString(),"userA", Uri.fromFile(File(FILE_PATHNAME)), "userA@gmail.com", "12345678")
            val userB = UserAuth(UUID.randomUUID().toString(),"userB", Uri.fromFile(File(FILE_PATHNAME)), "userB@gmail.com", "12345678")

            add(userA)
            add(userB)

            followers[userA.uuid] = hashSetOf()
            posts[userA.uuid] = hashSetOf()
            feeds[userA.uuid] = hashSetOf()

            followers[userB.uuid] = hashSetOf()
            posts[userB.uuid] = hashSetOf()
            feeds[userB.uuid] = hashSetOf()


            for (i in 0..30){
                val users = UserAuth(UUID.randomUUID().toString(), "user$i", null, "user$i@gmail.com", "123123123")

                userAuth.add(users)

            }
        }


//        userSession = userAuth.first()
//        followers[userSession!!.uuid]?.add(userAuth[2].uuid)

    }

}