package co.aladinjunior.instagram.profile

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth

interface Profile {
    interface Presenter : BasePresenter{
        fun fetchUserProfile(uuid: String?)
        fun fetchUserPosts(uuid: String?)
        fun followUser(uuid: String, following: Boolean)
        fun clearCache()
    }
    interface View : BaseView<Presenter> {
        fun displayUserProfile(userAuth: Pair<User, Boolean?>)
        fun displayRequestFailure(message: String)
        fun displayFullPosts(posts: List<Post>)


    }
}