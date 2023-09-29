package co.aladinjunior.instagram.post.data

import android.net.Uri
import co.aladinjunior.instagram.commom.base.BaseCallback

interface PostDataSource {

    suspend fun fetchPics(callback: BaseCallback<List<Uri>>) : List<Uri>
}