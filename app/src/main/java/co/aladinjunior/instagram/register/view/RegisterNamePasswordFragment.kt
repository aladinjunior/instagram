package co.aladinjunior.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.util.CustomTextWatcher
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentRegisterNamePasswordBinding
import co.aladinjunior.instagram.register.RegisterNamePassword
import java.lang.IllegalArgumentException

class RegisterNamePasswordFragment(var fragmentAttachListener: FragmentAttachListener? = null) :
    Fragment(R.layout.fragment_register_name_password),
    RegisterNamePassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null
    override lateinit var presenter: RegisterNamePassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)
        val repository = DependencyInjector.registerRepository()
        presenter = DependencyInjector.registerNamePasswordPresenter(this, repository)
        val email = arguments?.getString(KEY_EMAIL)
            ?: throw IllegalArgumentException("email nao pode ser vazio")
        Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()



        binding?.let {
            with(it) {
                registerEditTextName.addTextChangedListener(watcher)
                registerEditTextName.addTextChangedListener(CustomTextWatcher {
                    registerInputName.error = null
                })
                registerEditTextPassword.addTextChangedListener(watcher)
                registerEditTextPassword.addTextChangedListener(CustomTextWatcher {
                    registerInputPassword.error = null
                })
                registerEditTextConfirmPassword.addTextChangedListener(watcher)
                registerEditTextConfirmPassword.addTextChangedListener(CustomTextWatcher {
                    registerInputConfirmPassword.error = null
                })
                registerTextLogin.setOnClickListener {
                    activity?.finish()
                }
                registerBttnNext.setOnClickListener {
                    presenter.create(
                        email,
                        registerEditTextName.text.toString(),
                        registerEditTextPassword.text.toString(),
                        registerEditTextConfirmPassword.text.toString()
                    )
                }

            }
        }

    }

    val watcher = CustomTextWatcher {
        binding?.registerBttnNext?.isEnabled =
            binding?.registerEditTextName?.text.toString().isNotEmpty()
                    && binding?.registerEditTextPassword?.text.toString().isNotEmpty()
                    && binding?.registerEditTextConfirmPassword?.text.toString().isNotEmpty()
    }


    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun displayProgress(enabled: Boolean) {
        binding?.registerBttnNext?.showProgress(enabled)
    }

    override fun displayInvalidName(message: Int?) {
        binding?.registerInputName?.error = message?.let { getString(it) }
    }

    override fun displayInvalidPassword(message: Int?) {
        binding?.registerInputPassword?.error = message?.let { getString(it) }
    }

    override fun displayUnmatchPassword(message: Int?) {
        binding?.registerInputConfirmPassword?.error = message?.let { getString(it) }
    }

    override fun displayExistentUser(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun goToWelcomeScreen(name: String) {
        fragmentAttachListener?.goToWelcomeScreen(name)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}