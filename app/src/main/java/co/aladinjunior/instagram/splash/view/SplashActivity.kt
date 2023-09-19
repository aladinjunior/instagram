package co.aladinjunior.instagram.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.aladinjunior.instagram.commom.extension.animationEnd
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.ActivitySplashBinding
import co.aladinjunior.instagram.login.view.LoginActivity
import co.aladinjunior.instagram.main.view.MainActivity
import co.aladinjunior.instagram.splash.Splash

class SplashActivity : AppCompatActivity(), Splash.View {
    private lateinit var binding: ActivitySplashBinding
    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = DependencyInjector.splashPresenter(this, DependencyInjector.splashRepository())
        binding.splashIcon.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    presenter.authenticate()
                }
            })
            duration = 1000
            alpha(1.0f)
            start()
        }

    }



    override fun onAuthSuccess() {
        binding.splashIcon.animate().apply {
            setListener(animationEnd {
                val i = Intent(baseContext, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out) })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }

    }

    override fun onAuthFailure() {
        binding.splashIcon.animate().apply {
            setListener(animationEnd {
                val i = Intent(baseContext, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }

    }
}