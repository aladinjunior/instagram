package co.aladinjunior.instagram.add.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.*
import androidx.appcompat.widget.AppCompatButton
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.add.view.AddFragment.Companion.CAMERA_KEY
import co.aladinjunior.instagram.add.view.AddFragment.Companion.START_CAMERA
import co.aladinjunior.instagram.commom.util.Files


class CameraFragment : Fragment() {

    private lateinit var previewView: PreviewView
    private var imageCapture: ImageCapture? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewView = view.findViewById(R.id.camera)
        view.findViewById<AppCompatButton>(R.id.camera_bttn).setOnClickListener {
            takePhoto()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CAMERA_KEY){_, bundle ->
          val shouldStart =  bundle.getBoolean(START_CAMERA)
            if (shouldStart)
                startCamera()
        }
    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return
        val photoFile = Files.generateFile(requireActivity())
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val uri = Uri.fromFile(photoFile)
                setFragmentResult(URI_KEY, bundleOf(URI to uri))
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("error", "falha ao salvar a foto", exception)
            }
        })
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
                                         val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            imageCapture = ImageCapture.Builder()
                .setTargetResolution(Size(480, 480))
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            }catch (e: IllegalAccessException){
                Log.e("error", "attempt to access camera failed", e)
            }


        }, ContextCompat.getMainExecutor(requireContext()))
    }

    companion object{
        const val URI = "uri"
        const val URI_KEY = "uri_key"
    }
}

