package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.util.CustomTextWatcher
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentRegisterEmailBinding
import co.aladinjunior.instagram.register.Register

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), Register.View {
    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: Register.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        presenter = DependencyInjector.registerPresenter(this)


        binding?.let {
           with(it){
               registerEditTextEmail.addTextChangedListener(watcher)
               registerEditTextEmail.addTextChangedListener(CustomTextWatcher{
                   displayInvalidEmail(null)
               })
               registerTextLogin.setOnClickListener {
                   activity?.finish()
               }

               registerBttnNext.setOnClickListener {
                   presenter.registrate(registerEditTextEmail.text.toString())
               }
           }
        }
    }

    private val watcher = CustomTextWatcher {
        binding?.registerBttnNext?.isEnabled = binding?.registerEditTextEmail?.text.toString().isNotEmpty()
    }

    override fun displayInvalidEmail(message: Int?) {
        binding?.registerInputEmail?.error = message?.let { getString(message) }
    }



    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }




}