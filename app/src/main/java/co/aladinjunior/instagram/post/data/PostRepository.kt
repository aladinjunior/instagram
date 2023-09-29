package co.aladinjunior.instagram.post.data

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BaseCallback

class PostRepository(private val dataSource: PostDataSource) {

    suspend fun fetchPics(callback: BaseCallback<List<Uri>>) : List<Uri>{
        return dataSource.fetchPics(callback)
    }


}