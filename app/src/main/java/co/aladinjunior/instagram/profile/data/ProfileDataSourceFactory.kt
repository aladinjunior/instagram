package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: Cache<UserAuth>,
    private val postsProfileCache: Cache<List<Post>>,
) {

    fun createLocalDataSource() : ProfileDataSource{
        return ProfileLocalDataSource(profileCache, postsProfileCache)
    }

    fun createFromUser() : ProfileDataSource{
        if(profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsProfileCache)
        }
        return FakeProfileRequest()
    }
    fun createFromPosts() : ProfileDataSource{
        if(postsProfileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsProfileCache)
        }
        return FakeProfileRequest()
    }
}