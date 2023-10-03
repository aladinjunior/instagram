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

    fun createFromUser(uuid: String?) : ProfileDataSource{
        if (uuid != null){
            return ProfileFakeRemoteDataSource()
        }
        if(profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsProfileCache)
        }
        return ProfileFakeRemoteDataSource()
    }
    fun createFromPosts(uuid: String?) : ProfileDataSource{
        if (uuid != null){
            return ProfileFakeRemoteDataSource()
        }
        if(postsProfileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsProfileCache)
        }
        return ProfileFakeRemoteDataSource()
    }
}