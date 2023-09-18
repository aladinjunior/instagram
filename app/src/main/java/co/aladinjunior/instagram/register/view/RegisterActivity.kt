package co.aladinjunior.instagram.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.ActivityRegisterBinding
import co.aladinjunior.instagram.main.view.MainActivity
import co.aladinjunior.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import co.aladinjunior.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME

class RegisterActivity : AppCompatActivity(), FragmentAttachListener{


    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        fragmentManager(fragment)

    }

    override fun goToNamePasswordScreen(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)
        val fragment = RegisterNamePasswordFragment()
        fragment.arguments = args
        fragmentManager(fragment)

    }

    override fun goToMainScreen() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    override fun goToWelcomeScreen(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME, name)
        val fragment = RegisterWelcomeFragment()
        fragment.arguments = args
        fragmentManager(fragment)
    }

    override fun goToPhotoUploadScreen() {
        val fragment = RegisterPhotoUploadFragment()
        fragmentManager(fragment)
    }

    private fun fragmentManager(fragment: Fragment){
        if (supportFragmentManager.findFragmentById(R.id.fragment_register) == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_register, fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_register, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}