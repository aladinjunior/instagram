package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.FragmentRegisterEmailBinding

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email) {
    private var binding: FragmentRegisterEmailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}