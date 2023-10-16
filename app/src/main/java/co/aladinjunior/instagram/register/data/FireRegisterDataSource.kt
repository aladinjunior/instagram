package co.aladinjunior.instagram.register.data

import android.net.Uri
import co.aladinjunior.instagram.commom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireRegisterDataSource : RegisterDataSource {

    override fun registrate(email: String, callback: RegisterCallback) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) callback.onSuccess()
                else callback.onFailure("usuário já cadastrado!")
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "erro interno no servidor")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun registrate(
        email: String, name: String, password: String, callback: RegisterCallback
    ) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid

                if (uid == null) callback.onFailure("erro interno no servidor")
                else {
                    FirebaseFirestore.getInstance()
                        .collection("/users")
                        .document(uid)
                        .set(
                            hashMapOf(
                                "uid" to uid,
                                "name" to name,
                                "email" to email,
                                "followers" to 0,
                                "following" to 0,
                                "posts" to 0,
                                "photoUrl" to null

                            )
                        )
                        .addOnSuccessListener {
                            callback.onSuccess()
                        }
                        .addOnFailureListener{exception ->
                            callback.onFailure(exception.message ?: "erro interno")
                        }

                        .addOnCompleteListener{
                            callback.onComplete()
                        }


                }

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "erro interno do servidor!")
            }

    }

    override fun attachPhoto(uri: Uri, callback: RegisterCallback) {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null || uri.lastPathSegment == null){
            callback.onFailure("usuário não encontrado!")
            return
        }
        val storageRef = FirebaseStorage.getInstance().reference

        val imgRef = storageRef.child("/images")
            .child(uid)
            .child(uri.lastPathSegment!!)

        imgRef.putFile(uri)
            .addOnSuccessListener { result ->
                imgRef.downloadUrl
                    .addOnSuccessListener { res ->
                        val usersRef = FirebaseFirestore.getInstance().collection("/users").document(uid)

                        usersRef.get()
                            .addOnSuccessListener {document ->

                                val users = document.toObject(User::class.java)
                                val updatedUsers = users?.copy(photoUrl = res.toString())

                                if (updatedUsers != null){
                                    usersRef.set(updatedUsers)
                                        .addOnSuccessListener {
                                            callback.onSuccess()
                                        }
                                        .addOnFailureListener { e ->
                                            callback.onFailure(e.message ?: "erro ao atualizar a foto")
                                        }
                                        .addOnCompleteListener{
                                            callback.onComplete()
                                        }
                                }

                            }
                    }
            }
            .addOnFailureListener { e ->
                callback.onFailure(e.message ?: "falha ao subir a foto para o servidor")
            }
    }
}