package co.aladinjunior.instagram.register.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.commom.view.CustomDialog
import co.aladinjunior.instagram.commom.view.ImageCropperFragment.Companion.KEY_URI
import co.aladinjunior.instagram.databinding.FragmentRegisterPhotoUploadBinding
import co.aladinjunior.instagram.register.RegisterPhotoUpload

class RegisterPhotoUploadFragment(
    private var fragmentAttachListener: FragmentAttachListener? = null
) : Fragment(R.layout.fragment_register_photo_upload), RegisterPhotoUpload.View {

    private var binding: FragmentRegisterPhotoUploadBinding? = null
    override lateinit var presenter: RegisterPhotoUpload.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("croppedImage"){_, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            setProfileImage(uri)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoUploadBinding.bind(view)
        presenter = DependencyInjector.registerPhotoUploadPresenter(this, DependencyInjector.registerRepository())

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
            when (button.id) {
                R.string.photo -> fragmentAttachListener?.openCamera()
                R.string.gallery -> fragmentAttachListener?.openGallery()
            }
        }
        customDialog.show()
    }

    private fun setProfileImage(uri: Uri?){
        binding?.registerImgPhoto?.setImageURI(uri)
        uri?.let {
            presenter.attachPhotoToUser(it)
        }
    }

    override fun displayProgress(enabled: Boolean) {
        binding?.registerBttnAddPhoto?.showProgress(enabled)
    }

    override fun onAttachFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onAttachSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}