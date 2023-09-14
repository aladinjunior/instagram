package co.aladinjunior.instagram.commom.util

import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.FakeRequest
import co.aladinjunior.instagram.login.data.LoginRepository
import co.aladinjunior.instagram.login.presentation.LoginPresenter

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeRequest())
    }
    fun loginPresenter(view: Login.View, repository: LoginRepository) : Login.Presenter{
        return LoginPresenter(view, repository)
    }
}