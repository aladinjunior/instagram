package co.aladinjunior.instagram.add.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Post

class AddFakeRemoteDataSource : AddDataSource {
    override fun createPost(uuid: String, uri: Uri, captions: String, callback: BaseCallback<Boolean>) {

        Handler(Looper.getMainLooper()).postDelayed({
            var posts = Database.posts[uuid]
            if (posts == null){
                posts = mutableSetOf()
                Database.posts[uuid] = posts
            }
            val post = Post(uuid, null, captions, System.currentTimeMillis(), null)
            posts.add(post)

            var followers = Database.followers[uuid]
            if (followers == null){
                followers = mutableSetOf()
                Database.followers[uuid] = followers
            } else {
                for (follower in followers){
                    Database.feeds[follower]?.add(post)
                }
                Database.feeds[uuid]?.add(post)
            }

            callback.onSuccess(true)
        },1000)
    }
}