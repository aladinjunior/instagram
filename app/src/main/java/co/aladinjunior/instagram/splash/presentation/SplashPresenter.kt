package co.aladinjunior.instagram.splash.presentation

import co.aladinjunior.instagram.splash.Splash
import co.aladinjunior.instagram.splash.data.SplashCallback
import co.aladinjunior.instagram.splash.data.SplashRepository

class SplashPresenter(var view: Splash.View? = null,
                      private val repository: SplashRepository) : Splash.Presenter {

    override fun authenticate() {
        repository.authenticate(object : SplashCallback{
            override fun onSuccess() {
                view?.onAuthSuccess()
            }

            override fun onFailure() {
                view?.onAuthFailure()
            }
        })
    }

    override fun onDestroy() {
        view = null
    }

}