package co.aladinjunior.instagram.post.presentation

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.post.Post
import co.aladinjunior.instagram.post.data.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostPresenter(
    var view: Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

    private val job = Job()
    override var coroutineContext: CoroutineContext = Dispatchers.IO + job


    override fun fetchPics() {
        launch {
            withContext(Dispatchers.Main){
                repository.fetchPics(object : BaseCallback<List<Uri>>{
                    override fun onSuccess(data: List<Uri>) {
                        view?.loadAllPics(data)
                    }

                    override fun onFailure(message: String) {
                        view?.showMessageOnScreen(message)
                    }
                })
            }

        }

    }

    override fun onDestroy() {
        view = null
        job.cancel()
    }
}