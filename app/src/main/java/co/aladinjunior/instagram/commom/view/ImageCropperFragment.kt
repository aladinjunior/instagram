package co.aladinjunior.instagram.commom.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.FragmentImageCropperBinding
import java.io.File

class ImageCropperFragment : Fragment(R.layout.fragment_image_cropper) {

    private var binding: FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = arguments?.getParcelable<Uri>(KEY_URI)
        binding = FragmentImageCropperBinding.bind(view)
        binding?.let {
            with(it){
                cropperContainer.setAspectRatio(1,1)
                cropperContainer.setFixedAspectRatio(true)
                cropperContainer.setImageUriAsync(uri)

                cropperBttnCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                cropperContainer.setOnCropImageCompleteListener { view, result ->
                    parentFragmentManager.popBackStack()

                    setFragmentResult("croppedImage", bundleOf(KEY_URI to result.uri))
                }

                cropperBttnSave.setOnClickListener {
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    dir?.let{
                        val savedUri = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpg"))
                        cropperContainer.saveCroppedImageAsync(savedUri)
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
    companion object{
        const val KEY_URI = "key_uri"
    }
}