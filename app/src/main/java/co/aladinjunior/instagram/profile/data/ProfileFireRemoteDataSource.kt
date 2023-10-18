package co.aladinjunior.instagram.profile.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
                                .document(FirebaseAuth.getInstance().uid!!)
                                .collection("followers")
                                .whereEqualTo("uid", userUuid)
                                .get()
                                .addOnSuccessListener { res ->
                                    callback.onSuccess(Pair(user, !res.isEmpty))
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

    }
}