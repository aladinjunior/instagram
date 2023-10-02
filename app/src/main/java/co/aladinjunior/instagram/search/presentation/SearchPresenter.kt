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
                 view?.displayUsers(data)
            }

            override fun onFailure(message: String) {
                view?.displayEmptyUsers(message)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}