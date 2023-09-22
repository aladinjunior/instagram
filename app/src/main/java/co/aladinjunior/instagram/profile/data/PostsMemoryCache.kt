package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.model.Post

object PostsMemoryCache : ProfileCache<List<Post>> {
    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(response: String?): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>) {
        posts = data
    }
}