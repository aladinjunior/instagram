package co.aladinjunior.instagram.register.data

interface RegisterDataSource {
    fun registrate(email: String, callback: RegisterCallback)
    fun registrate(email: String, name: String, password: String, callback: RegisterCallback)
}