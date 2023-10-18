package co.aladinjunior.instagram.profile.presentation

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.profile.Profile
import co.aladinjunior.instagram.profile.data.ProfileRepository


class ProfilePresenter(
    var view: Profile.View? = null,
    private val repository: ProfileRepository
) : Profile.Presenter {
    override fun clearCache() {
        repository.clear()
    }

    override fun followUser(uuid: String, following: Boolean) {
        repository.followUser(uuid, following, object : BaseCallback<Boolean>{
            override fun onSuccess(data: Boolean) {}

            override fun onFailure(message: String) {}
        })
    }

    override fun fetchUserProfile(uuid: String?) {
        repository.fetchUserProfile(uuid, object : BaseCallback<Pair<User, Boolean?>>{
            override fun onSuccess(data: Pair<User, Boolean?>) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }
        })
    }

    override fun fetchUserPosts(uuid: String?) {
        repository.fetchUserPosts(uuid, object : BaseCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayRequestFailure("não há postagens disponiveis")
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }

}
