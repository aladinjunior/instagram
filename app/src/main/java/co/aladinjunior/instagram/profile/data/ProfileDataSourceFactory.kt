package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.Cache
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
    private val postsProfileCache: Cache<List<Post>>,
) {

    fun createRemoteDataSource() : ProfileDataSource{
        return ProfileFakeRemoteDataSource()
    }

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