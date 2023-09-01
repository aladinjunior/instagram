package co.aladinjunior.instagram.register.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.custom.view.CustomDialog

class RegisterPhotoUploadFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_photo_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customDialog = CustomDialog(requireContext())

        customDialog.addButtons(R.string.photo, R.string.gallery) {button ->
           when{
               button.id == R.string.photo -> Log.i("log", "foto")
               button.id == R.string.gallery -> Log.i("log", "galeria")
           }
        }
        customDialog.show()
    }
}