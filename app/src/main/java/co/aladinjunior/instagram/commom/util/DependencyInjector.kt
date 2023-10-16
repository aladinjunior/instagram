package co.aladinjunior.instagram.commom.util

import android.content.Context
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
import co.aladinjunior.instagram.post.Post
import co.aladinjunior.instagram.post.data.PostLocalDataSource
import co.aladinjunior.instagram.post.data.PostRepository
import co.aladinjunior.instagram.post.presentation.GalleryPresenter
import co.aladinjunior.instagram.post.view.GalleryFragment
import co.aladinjunior.instagram.profile.Profile
import co.aladinjunior.instagram.profile.data.*
import co.aladinjunior.instagram.profile.presentation.ProfilePresenter
import co.aladinjunior.instagram.register.RegisterEmail
import co.aladinjunior.instagram.register.RegisterNamePassword
import co.aladinjunior.instagram.register.RegisterPhotoUpload
import co.aladinjunior.instagram.register.data.FakeRegisterDataSource
import co.aladinjunior.instagram.register.data.FireRegisterDataSource
import co.aladinjunior.instagram.register.data.RegisterRepository
import co.aladinjunior.instagram.register.presentation.RegisterEmailPresenter
import co.aladinjunior.instagram.register.presentation.RegisterNamePasswordPresenter
import co.aladinjunior.instagram.register.presentation.RegisterPhotoUploadPresenter
import co.aladinjunior.instagram.search.Search
import co.aladinjunior.instagram.search.data.SearchFakeRemoteDataSource
import co.aladinjunior.instagram.search.data.SearchRepository
import co.aladinjunior.instagram.search.presentation.SearchPresenter
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
        return RegisterRepository(FireRegisterDataSource())
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
    fun galleryPresenter(view: Post.View, repository: PostRepository) : GalleryPresenter {
        return GalleryPresenter(view, repository)
    }
    fun postRepository(context: Context) : PostRepository{
        return PostRepository(PostLocalDataSource(context))
    }

    fun searchPresenter(view: Search.View, repository: SearchRepository) : SearchPresenter {
        return SearchPresenter(view, repository)
    }
    fun searchRepository() : SearchRepository{
        return SearchRepository(SearchFakeRemoteDataSource())
    }

}