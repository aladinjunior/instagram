package co.aladinjunior.instagram.home.presentation

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.home.Home
import co.aladinjunior.instagram.home.data.HomeRepository

class HomePresenter(
    var view: Home.View? = null,
    private val repository: HomeRepository
) : Home.Presenter{
    override fun fetchPosts() {
        repository.fetchPosts(object : BaseCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()){
                    //TODO: SHOW ON UI THAT THERE IS NO POSTS
                }else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayPostsFailure(message)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}