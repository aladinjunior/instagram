package co.aladinjunior.instagram.register.data

interface RegisterCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()

}