package co.aladinjunior.instagram.commom.util

import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.FakeLoginRequest
import co.aladinjunior.instagram.login.data.LoginRepository
import co.aladinjunior.instagram.login.presentation.LoginPresenter
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.RegisterNamePassword
import co.aladinjunior.instagram.register.RegisterPhotoUpload
import co.aladinjunior.instagram.register.data.FakeRegisterRequest
import co.aladinjunior.instagram.register.data.RegisterRepository
import co.aladinjunior.instagram.register.presentation.RegisterEmailPresenter
import co.aladinjunior.instagram.register.presentation.RegisterNamePasswordPresenter
import co.aladinjunior.instagram.register.presentation.RegisterPhotoUploadPresenter

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeLoginRequest())
    }
    fun loginPresenter(view: Login.View, repository: LoginRepository) : Login.Presenter{
        return LoginPresenter(view, repository)
    }

    fun registerRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterRequest())
    }
    fun registerEmailPresenter(view: RegisterEmail.View, repository: RegisterRepository) : RegisterEmail.Presenter{
        return RegisterEmailPresenter(view, repository)
    }

    fun registerNamePasswordPresenter(view: RegisterNamePassword.View, repository: RegisterRepository) : RegisterNamePasswordPresenter{
        return RegisterNamePasswordPresenter(view, repository)
    }
    fun registerPhotoUploadPresenter(view: RegisterPhotoUpload.View, repository: RegisterRepository) : RegisterPhotoUpload.Presenter{
        return RegisterPhotoUploadPresenter(view, repository)
    }
}