package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.ActivityRegisterBinding

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

    }

    private fun fragmentManager(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_register, fragment)
            .commit()
    }
}