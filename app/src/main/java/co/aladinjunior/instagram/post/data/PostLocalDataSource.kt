package co.aladinjunior.instagram.post.data

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import co.aladinjunior.instagram.commom.base.BaseCallback

class PostLocalDataSource(private val context: Context) : PostDataSource {

    override suspend fun fetchPics(callback: BaseCallback<List<Uri>>): List<Uri> {
        val photos = mutableListOf<Uri>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
        )


        val cursor = context.contentResolver.query(
            collection, projection, null, null,
            "${MediaStore.Images.Media._ID} DESC"
        )




        cursor?.let {
            val columnId = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            if (!it.moveToFirst()){
                callback.onFailure("sem fotos no armazenamento!")
            } else {
                do{
                    val id = it.getLong(columnId)
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    photos.add(uri)
                    if(photos.size == 99) break
                } while (it.moveToNext())
            }

        }
        callback.onSuccess(photos)
        cursor?.close()
        return photos

    }
}