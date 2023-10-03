package co.aladinjunior.instagram.search.presentation

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.search.Search
import co.aladinjunior.instagram.search.data.SearchRepository

class SearchPresenter(
    var view: Search.View?,
    private val repository: SearchRepository
) : Search.Presenter {

    override fun fetchUsers(name: String) {
        repository.fetchUsers(name, object : BaseCallback<List<UserAuth>>{
            override fun onSuccess(data: List<UserAuth>) {
                if(data.isEmpty()) view?.displayEmptyUsers()
                else view?.displayUsers(data)
            }

            override fun onFailure(message: String) {
                view?.displayEmptyUsers()
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}