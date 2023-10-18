package co.aladinjunior.instagram.home.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.lang.IllegalStateException

class HomeFireDataSource : HomeDataSource {

    override fun fetchPost(uuid: String, callback: BaseCallback<List<Post>>) {
        val uid = FirebaseAuth.getInstance().uid ?: throw IllegalStateException("usuário não encontrado")
        FirebaseFirestore.getInstance()
            .collection("/feeds")
            .document(uid)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { response ->
                val feed = mutableListOf<Post>()
                val documents = response.documents
                for(document in documents){
                    val posts = document.toObject(Post::class.java)
                    posts?.let{ feed.add(it) }
                }

                callback.onSuccess(feed)
            }
            .addOnFailureListener { e ->
                callback.onFailure(e.message ?: "erro ao carregar o feed")
            }


    }
}