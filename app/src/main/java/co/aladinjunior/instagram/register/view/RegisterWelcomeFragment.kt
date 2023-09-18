package co.aladinjunior.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterWelcomeFragment(var fragmentAttachListener: FragmentAttachListener? = null) : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME)
        binding?.let {
            with(it){
                registerBttnNext.isEnabled = true
                registerTextWelcome.text = getString(R.string.welcome_to_instagram, name)
                registerBttnNext.setOnClickListener {
                    fragmentAttachListener?.goToPhotoUploadScreen()
                }
            }
        }


    }

    companion object{
        const val KEY_NAME = "key_name"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}