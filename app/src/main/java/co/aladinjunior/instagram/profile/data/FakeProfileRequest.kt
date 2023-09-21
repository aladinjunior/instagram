package co.aladinjunior.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.UserAuth

class FakeProfileRequest : ProfileDataSource {

    override fun fetchUserProfile(userUuid: String, callback: BaseCallback<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.userAuth.firstOrNull { userUuid == it.uuid }
            if (userAuth != null) callback.onSuccess(userAuth)
            else callback.onFailure("usuário não encontrado")
        }, 2000)


    }

    override fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Photo>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val posts = Database.posts[userUuid]

            callback.onSuccess(posts?.toList() ?: emptyList())

        }, 2000)
    }

}