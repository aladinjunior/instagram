package co.aladinjunior.instagram.commom.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.view.ViewPropertyAnimator
import androidx.appcompat.app.AppCompatActivity

fun Activity.animationEnd(callback: () -> Unit) : AnimatorListenerAdapter{
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