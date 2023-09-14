package co.aladinjunior.instagram.register.data

interface RegisterEmailDataSource {
    fun registrate(email: String, callback: RegisterEmailCallback)
}