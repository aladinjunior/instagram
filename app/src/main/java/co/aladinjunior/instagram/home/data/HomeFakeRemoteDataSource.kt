package co.aladinjunior.instagram.home.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Post


class HomeFakeRemoteDataSource : HomeDataSource {

    override fun fetchPost(uuid: String, callback: BaseCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val posts = Database.posts[uuid]

            callback.onSuccess(posts?.toList() ?: emptyList())

        }, 2000)
    }

}