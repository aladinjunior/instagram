package co.aladinjunior.instagram.commom.model


data class User(
    val uid: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val email: String? = null,
    val posts: Int = 0,
    val following: Int = 0,
    val followers: Int = 0,
)