package co.aladinjunior.instagram.commom.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.view.ViewPropertyAnimator
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun animationEnd(callback: () -> Unit) : AnimatorListenerAdapter{
    return object : AnimatorListenerAdapter(){
        override fun onAnimationEnd(animation: Animator?) {
            callback.invoke()
        }
    }
}

fun  < T : AppCompatActivity> Activity.customIntent(animator: ViewPropertyAnimator, destinationClass: Class<T>){
    animator.apply {
        setListener(animationEnd {
            val i = Intent(baseContext, destinationClass)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        })
        duration = 1000
        startDelay = 1000
        alpha(0.0f)
        start()
    }
}

fun AppCompatActivity.replaceFragment(@IdRes id: Int, fragment: Fragment) {
    if (supportFragmentManager.findFragmentById(id) == null) {
        supportFragmentManager.beginTransaction().apply {
            add(id, fragment, fragment.javaClass.simpleName)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment, fragment.javaClass.simpleName)
            addToBackStack(null)
            commit()
        }
    }

}