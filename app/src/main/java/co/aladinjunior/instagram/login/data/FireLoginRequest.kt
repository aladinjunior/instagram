package co.aladinjunior.instagram.login.data

import com.google.firebase.auth.FirebaseAuth

class FireLoginRequest : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                if (result.user != null){
                    callback.onSuccess()
                } else {
                    callback.onFailure("usuário não cadastrado")
                }

            }
            .addOnFailureListener { e ->
                callback.onFailure(e.message ?: "erro interno no servidor")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}