package co.aladinjunior.instagram.commom.util

import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.FakeLoginRequest
import co.aladinjunior.instagram.login.data.LoginRepository
import co.aladinjunior.instagram.login.presentation.LoginPresenter
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.data.FakeRegisterEmailRequest
import co.aladinjunior.instagram.register.data.RegisterEmailRepository
import co.aladinjunior.instagram.register.presentation.RegisterEmailPresenter

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeLoginRequest())
    }
    fun loginPresenter(view: Login.View, repository: LoginRepository) : Login.Presenter{
        return LoginPresenter(view, repository)
    }

    fun registerEmailRepository() : RegisterEmailRepository{
        return RegisterEmailRepository(FakeRegisterEmailRequest())
    }
    fun registerEmailPresenter(view: RegisterEmail.View, repository: RegisterEmailRepository) : RegisterEmail.Presenter{
        return RegisterEmailPresenter(view, repository)
    }
}