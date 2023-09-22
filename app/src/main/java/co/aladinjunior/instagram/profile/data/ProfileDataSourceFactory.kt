package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>
) {

    fun createLocalDataSource() : ProfileDataSource{
        return ProfileLocalDataSource(profileCache)
    }

    fun createFromUser() : ProfileDataSource{
        if(profileCache.isCached()){
            return ProfileLocalDataSource(profileCache)
        }
        return FakeProfileRequest()
    }
}