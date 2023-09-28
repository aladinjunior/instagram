package co.aladinjunior.instagram.add.view

import android.Manifest
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
import co.aladinjunior.instagram.add.view.CameraFragment.Companion.URI
import co.aladinjunior.instagram.add.view.CameraFragment.Companion.URI_KEY
import co.aladinjunior.instagram.commom.util.CustomTabSelected
import co.aladinjunior.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : Fragment(R.layout.fragment_add) {
    private var binding: FragmentAddBinding? = null
    private lateinit var adapter: AddViewPagerAdapter



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
                startActivity(i)
            }

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


    private val tabListener = CustomTabSelected { tab ->
        if (tab?.text == getString(adapter.tabs[0])) {
            startCamera()
        }
    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
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
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        const val CAMERA_KEY = "camera_key"
        const val START_CAMERA = "start_camera"
    }

}