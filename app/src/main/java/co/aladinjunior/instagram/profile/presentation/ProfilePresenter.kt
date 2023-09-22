package co.aladinjunior.instagram.profile.presentation

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.Photo
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.profile.Profile
import co.aladinjunior.instagram.profile.data.ProfileRepository
import java.lang.IllegalStateException

class ProfilePresenter(
    var view: Profile.View? = null,
    val repository: ProfileRepository
) : Profile.Presenter {

    override lateinit var state: UserAuth


    override fun fetchUserProfile() {
       val user = Database.userSession ?: throw IllegalStateException("usuario nao encontrado")
        repository.fetchUserProfile(user.uuid , object : BaseCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                state = data
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }
        })
    }
    override fun fetchUserPosts() {
        val userUUID = Database.userSession?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserPosts(userUUID, object : BaseCallback<List<Photo>> {
            override fun onSuccess(data: List<Photo>) {
                if (data.isEmpty()) {
                    //TODO: CREATE EMPTY POSTS
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
