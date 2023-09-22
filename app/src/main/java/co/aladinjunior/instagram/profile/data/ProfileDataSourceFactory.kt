package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
    private val postsCache: ProfileCache<List<Post>>,
) {

    fun createLocalDataSource() : ProfileDataSource{
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser() : ProfileDataSource{
        if(profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return FakeProfileRequest()
    }
    fun createFromPosts() : ProfileDataSource{
        if(postsCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return FakeProfileRequest()
    }
}