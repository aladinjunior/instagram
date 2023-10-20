package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class ProfileFireRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(userUuid: String, callback: BaseCallback<Pair<User, Boolean?>>) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(userUuid)
            .get()
            .addOnSuccessListener { response ->
                val user = response.toObject(User::class.java)

                when(user) {
                    null -> callback.onFailure("usuário não encontrado")
                    else -> {
                        if(user.uid == FirebaseAuth.getInstance().uid) {
                            callback.onSuccess(Pair(user, null))
                        } else {
                            FirebaseFirestore.getInstance()
                                .collection("/followers")
                                .document(userUuid)
                                .get()
                                .addOnSuccessListener { res ->
                                    if (!res.exists()){
                                        callback.onSuccess(Pair(user, false))
                                    } else {
                                        val list = res.get("followers") as List<String>
                                        callback.onSuccess(Pair(user, list.contains(FirebaseAuth.getInstance().uid)))
                                    }

                                }
                                .addOnFailureListener{ e ->
                                    callback.onFailure(e.message ?: "erro ao carregar seguidores")
                                }

                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                callback.onFailure(e.message ?: "erro ao carregar usuário")
            }
    }

    override fun fetchUserPosts(userUuid: String, callback: BaseCallback<List<Post>>) {

        FirebaseFirestore.getInstance()
            .collection("posts")
            .document(userUuid)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val posts = mutableListOf<Post>()
                for (document in documents) {
                    val post = document.toObject(Post::class.java)
                    post?.let {
                        posts.add(it)
                    }
                }
                callback.onSuccess(posts)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao buscar posts")
            }
        
    }

    override fun followUser(userUuid: String, isFollow: Boolean, callback: BaseCallback<Boolean>) {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseFirestore.getInstance()
            .collection("/followers")
            .document(userUuid)
            .update("followers", if (isFollow) FieldValue.arrayUnion(uid)
            else FieldValue.arrayRemove(uid))
            .addOnSuccessListener { res ->
                callback.onSuccess(true)
            }
            .addOnFailureListener { e ->
                val error = e as FirebaseFirestoreException

                if (error.code == FirebaseFirestoreException.Code.NOT_FOUND){
                    FirebaseFirestore.getInstance()
                        .collection("/followers")
                        .document(userUuid)
                        .set(
                            hashMapOf(
                                "follower" to listOf(uid)
                            )
                        )
                        .addOnSuccessListener { res ->
                            callback.onSuccess(true)
                        }
                        .addOnFailureListener { e ->
                            callback.onFailure(e.message ?: "falha ao criar seguidor")
                        }

                }

                callback.onFailure(e.message ?: "falha ao atualizar seguidor")
            }
    }
}