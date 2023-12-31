package co.aladinjunior.instagram.post.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.add.view.AddActivity
import co.aladinjunior.instagram.post.view.CameraFragment.Companion.URI
import co.aladinjunior.instagram.post.view.CameraFragment.Companion.URI_KEY
import co.aladinjunior.instagram.commom.util.CustomTabSelected
import co.aladinjunior.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : Fragment(R.layout.fragment_add) {
    private var binding: FragmentAddBinding? = null
    private lateinit var adapter: AddViewPagerAdapter
    private var addListener: AddListener? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        if (savedInstanceState == null)
        setupViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(URI_KEY){_, bundle ->
            val uri = bundle.getParcelable<Uri>(URI)
            uri?.let {
                val i = Intent(requireContext(), AddActivity::class.java)
                    .putExtra(URI, uri)
                getActivityResult.launch(i)

            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddListener){
            addListener = context
        }

    }

    private fun setupViews() {
        val viewPager = binding?.addViewpager
        val tabLayout = binding?.addTab
        adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            tabLayout.addOnTabSelectedListener(tabListener)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            getPermission.launch(REQUIRED_PERMISSION)
        }
    }

    interface AddListener{
        fun onPostCreated()
    }
    private val tabListener = CustomTabSelected { tab ->
        if (tab?.text == getString(adapter.tabs[0])) {
            startCamera()
        }
    }

    private val getActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            addListener?.onPostCreated()
        }
    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (allPermissionsGranted()) startCamera()
            else Toast.makeText(requireContext(), R.string.camera_access_denied, Toast.LENGTH_SHORT)
                .show()
        }

    private fun startCamera() {
        setFragmentResult(CAMERA_KEY, bundleOf(START_CAMERA to true))
    }

    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[0]
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    REQUIRED_PERMISSION[1]
                ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        const val CAMERA_KEY = "camera_key"
        const val START_CAMERA = "start_camera"
    }

}