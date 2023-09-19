package co.aladinjunior.instagram.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.view.ImageCropperFragment
import co.aladinjunior.instagram.commom.view.ImageCropperFragment.Companion.KEY_URI
import co.aladinjunior.instagram.databinding.ActivityRegisterBinding
import co.aladinjunior.instagram.main.view.MainActivity
import co.aladinjunior.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import co.aladinjunior.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

class RegisterActivity : AppCompatActivity(), FragmentAttachListener{


    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        fragmentManager(fragment)

    }

    override fun goToNamePasswordScreen(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)
        val fragment = RegisterNamePasswordFragment()
        fragment.arguments = args
        fragmentManager(fragment)

    }

    override fun goToMainScreen() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
        uri?.let { openImageCropper(it) }
    }

    private val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) {saved ->
        if (saved) openImageCropper(currentPhoto)

    }

    override fun openCamera() {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (i.resolveActivity(packageManager) != null){
            val photoImage: File? = try{
                createImagePhoto()
            } catch (e: IOException){
                Log.e("log", e.message, e)
                null
            }

            photoImage?.also {
                val photoUri = FileProvider.getUriForFile(this, "co.aladinjunior.instagram.fileprovider", it)
                currentPhoto = photoUri
                getCamera.launch(photoUri)
            }
        }

    }

    @Throws(IOException::class)
    private fun createImagePhoto() : File{
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
       return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
    }

    override fun openGallery() {
        getContent.launch("image/*")
    }

    override fun goToWelcomeScreen(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME, name)
        val fragment = RegisterWelcomeFragment()
        fragment.arguments = args
        fragmentManager(fragment)
    }

    override fun goToPhotoUploadScreen() {
        val fragment = RegisterPhotoUploadFragment()
        fragmentManager(fragment)
    }

    private fun fragmentManager(fragment: Fragment){
        if (supportFragmentManager.findFragmentById(R.id.fragment_register) == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_register, fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_register, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun openImageCropper(uri: Uri){
        val args = Bundle()
        args.putParcelable(KEY_URI, uri)
        val fragment = ImageCropperFragment()
        fragment.arguments = args
        fragmentManager(fragment)
    }
}