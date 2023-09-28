package co.aladinjunior.instagram.commom.util

import co.aladinjunior.instagram.add.Add
import co.aladinjunior.instagram.add.data.AddFakeRemoteDataSource
import co.aladinjunior.instagram.add.data.AddLocalDataSource
import co.aladinjunior.instagram.add.data.AddRepository
import co.aladinjunior.instagram.add.presentation.AddPresenter
import co.aladinjunior.instagram.home.Home
import co.aladinjunior.instagram.home.data.FeedMemoryCache
import co.aladinjunior.instagram.home.data.HomeDataSourceFactory
import co.aladinjunior.instagram.home.data.HomeRepository
import co.aladinjunior.instagram.home.presentation.HomePresenter
import co.aladinjunior.instagram.login.Login
import co.aladinjunior.instagram.login.data.FakeLoginRequest
import co.aladinjunior.instagram.login.data.LoginRepository
import co.aladinjunior.instagram.login.presentation.LoginPresenter
import co.aladinjunior.instagram.profile.Profile
import co.aladinjunior.instagram.profile.data.*
import co.aladinjunior.instagram.profile.presentation.ProfilePresenter
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.RegisterNamePassword
import co.aladinjunior.instagram.register.RegisterPhotoUpload
import co.aladinjunior.instagram.register.data.FakeRegisterRequest
import co.aladinjunior.instagram.register.data.RegisterRepository
import co.aladinjunior.instagram.register.presentation.RegisterEmailPresenter
import co.aladinjunior.instagram.register.presentation.RegisterNamePasswordPresenter
import co.aladinjunior.instagram.register.presentation.RegisterPhotoUploadPresenter
import co.aladinjunior.instagram.splash.Splash
import co.aladinjunior.instagram.splash.data.FakeSplashRequest
import co.aladinjunior.instagram.splash.data.SplashRepository
import co.aladinjunior.instagram.splash.presentation.SplashPresenter

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

    fun splashPresenter(view: Splash.View, repository: SplashRepository) : Splash.Presenter{
        return SplashPresenter(view, repository)
    }
    fun splashRepository() : SplashRepository{
        return SplashRepository(FakeSplashRequest())
    }
    fun profilePresenter(view: Profile.View, repository: ProfileRepository) : Profile.Presenter{
        return ProfilePresenter(view, repository)
    }
    fun profileRepository() : ProfileRepository{
        return ProfileRepository(ProfileDataSourceFactory(MemoryProfileCache, PostsMemoryProfileCache))
    }
    fun homePresenter(view: Home.View, repository: HomeRepository) : Home.Presenter{
        return HomePresenter(view, repository)
    }
    fun homeRepository() : HomeRepository{
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }
    fun addPresenter(view: Add.View, repository: AddRepository) : Add.Presenter{
        return AddPresenter(view, repository)
    }
    fun addRepository() : AddRepository{
        return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
    }
}