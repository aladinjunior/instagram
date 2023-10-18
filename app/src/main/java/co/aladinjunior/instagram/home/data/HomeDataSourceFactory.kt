package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Post

class HomeDataSourceFactory(private val postCache: Cache<List<Post>>) {

    fun createLocalDataSoure() : HomeLocalDataSource {
        return HomeLocalDataSource(postCache)
    }

    fun createFromPosts() : HomeDataSource{
        if (postCache.isCached()) return HomeLocalDataSource(postCache)
        return HomeFireDataSource()

    }
}