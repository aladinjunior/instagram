package co.aladinjunior.instagram.register.data

class RegisterEmailRepository(private val dataSource: RegisterEmailDataSource) {

    fun registrate(email: String, callback: RegisterEmailCallback){
        dataSource.registrate(email, callback)
    }

}