package co.aladinjunior.instagram.profile

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth

interface Profile {
    interface Presenter : BasePresenter{
        fun fetchUserProfile()
        fun fetchUserPosts()
    }
    interface View : BaseView<Presenter> {
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayFullPosts(posts: List<Post>)


    }
}