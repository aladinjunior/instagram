package co.aladinjunior.instagram.register.data

class RegisterRepository(private val dataSource: RegisterDataSource) {

    fun registrate(email: String, callback: RegisterCallback){
        dataSource.registrate(email, callback)
    }

    fun registrate(email: String, name: String, password: String, callback: RegisterCallback){
        dataSource.registrate(email, name, password, callback)
    }

}