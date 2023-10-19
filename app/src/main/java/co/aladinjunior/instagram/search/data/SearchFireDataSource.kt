package co.aladinjunior.instagram.search.data

import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.User
import co.aladinjunior.instagram.commom.model.UserAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SearchFireDataSource : SearchDataSource {

    override fun fetchUsers(name: String, callback: BaseCallback<List<User>>) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", name  + "\uf8ff")
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val users = mutableListOf<User>()
                for (document in documents){
                    val user = document.toObject(User::class.java)
                    user?.let {
                        if (user.uid != FirebaseAuth.getInstance().uid)
                        users.add(it)
                    }

                    callback.onSuccess(users)
                }
            }
            .addOnFailureListener { e ->
                callback.onFailure(e.message ?: "erro ao procurar usuário")
            }

    }
}