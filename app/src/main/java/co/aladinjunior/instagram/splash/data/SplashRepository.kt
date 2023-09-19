package co.aladinjunior.instagram.splash.data

class SplashRepository(private val dataSource: SplashDataSource) {

    fun authenticate(callback: SplashCallback){
        dataSource.authenticate(callback)
    }
}