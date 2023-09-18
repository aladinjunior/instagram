package co.aladinjunior.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.view.CustomDialog
import co.aladinjunior.instagram.databinding.FragmentRegisterPhotoUploadBinding

class RegisterPhotoUploadFragment(
    var fragmentAttachListener: FragmentAttachListener? = null
) : Fragment(R.layout.fragment_register_photo_upload) {

    private var binding: FragmentRegisterPhotoUploadBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoUploadBinding.bind(view)

        binding?.let {
            with(it) {
                registerBttnAddPhoto.isEnabled = true
                registerBttnAddPhoto.setOnClickListener {
                    val customDialog = CustomDialog(requireContext())
                    customDialog.addButtons(R.string.photo, R.string.gallery) { button ->
                        when {
                            button.id == R.string.photo -> Log.i("log", "foto")
                            button.id == R.string.gallery -> Log.i("log", "galeria")
                        }
                    }
                    customDialog.show()
                }

                registerBttnSkip.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}