package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentRegisterEmailBinding
import co.aladinjunior.instagram.register.Register

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), Register.View {
    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: Register.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)


        binding?.registerBttnNext?.setOnClickListener {


        }

    }
    override fun displayProgress(enabled: Boolean) {
        binding?.registerBttnNext?.showProgress(enabled)
    }

    override fun displayInvalidEmail(message: Int?) {
        binding?.registerInputEmail?.error = message?.let { getString(message) }
    }

    override fun cadastrateEmail(email: String) {
       // GO TO NEXT SCREEN
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }




}