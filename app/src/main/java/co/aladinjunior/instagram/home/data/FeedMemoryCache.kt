package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Post

object FeedMemoryCache : Cache<List<Post>> {
    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(response: String?): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>?) {
        posts = data
    }
}