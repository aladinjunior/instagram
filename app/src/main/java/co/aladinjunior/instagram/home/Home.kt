package co.aladinjunior.instagram.home

import co.aladinjunior.instagram.commom.base.BasePresenter
import co.aladinjunior.instagram.commom.base.BaseView
import co.aladinjunior.instagram.commom.model.Post

interface Home {
    interface Presenter : BasePresenter{
        fun fetchPosts()
        fun clearCache()
    }
    interface View{
        fun displayPostsFailure(message: String)
        fun displayFullPosts(posts: List<Post>)
    }
}