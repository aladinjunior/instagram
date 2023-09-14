package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.util.CustomTextWatcher
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentRegisterEmailBinding
import co.aladinjunior.instagram.register.RegisterEmail

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        presenter = DependencyInjector.registerEmailPresenter(this, DependencyInjector.registerEmailRepository())


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

    override fun displayProgress(enabled: Boolean) {
        binding?.registerBttnNext?.showProgress(enabled)
    }

    private val watcher = CustomTextWatcher {
        binding?.registerBttnNext?.isEnabled = binding?.registerEditTextEmail?.text.toString().isNotEmpty()
    }

    override fun displayInvalidEmail(message: Int?) {
        binding?.registerInputEmail?.error = message?.let { getString(message) }

    }

    override fun onEmailFailure(message: String) {
        binding?.registerInputEmail?.error = message
    }

    override fun goToNamePasswordScreen() {
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }




}