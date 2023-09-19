package co.aladinjunior.instagram.register.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.view.CustomDialog
import co.aladinjunior.instagram.commom.view.ImageCropperFragment.Companion.KEY_URI
import co.aladinjunior.instagram.databinding.FragmentRegisterPhotoUploadBinding

class RegisterPhotoUploadFragment(
    var fragmentAttachListener: FragmentAttachListener? = null
) : Fragment(R.layout.fragment_register_photo_upload) {

    private var binding: FragmentRegisterPhotoUploadBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("croppedImage"){requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            setProfileImage(uri)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoUploadBinding.bind(view)

        binding?.let {
            with(it) {
                registerBttnAddPhoto.isEnabled = true
                registerBttnAddPhoto.setOnClickListener {
                   openDialog()
                }

                registerBttnSkip.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
            }
        }


    }

    private fun openDialog(){
        val customDialog = CustomDialog(requireContext())
        customDialog.addButtons(R.string.photo, R.string.gallery) { button ->
            when {
                button.id == R.string.photo -> fragmentAttachListener?.openCamera()
                button.id == R.string.gallery -> fragmentAttachListener?.openGallery()
            }
        }
        customDialog.show()
    }

    private fun setProfileImage(uri: Uri?){
        binding?.registerImgPhoto?.setImageURI(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        super.onDestroy()
    }
}